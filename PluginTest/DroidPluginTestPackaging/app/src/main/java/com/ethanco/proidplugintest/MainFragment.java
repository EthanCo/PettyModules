package com.ethanco.proidplugintest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ethanco.nova.AdapterWrap;
import com.ethanco.nova.NovaRecyclerView;
import com.ethanco.proidplugintest.utils.LoadingDialog;
import com.morgoo.droidplugin.pm.PluginManager;
import com.morgoo.facade.ApkItem;
import com.morgoo.facade.DroidPluginFacade;
import com.morgoo.facade.callback.FoundPluginCallback;
import com.morgoo.facade.callback.InstallCallback;
import com.morgoo.facade.callback.QueryCallback;

import static com.morgoo.helper.compat.PackageManagerCompat.INSTALL_FAILED_NOT_SUPPORT_ABI;
import static com.morgoo.helper.compat.PackageManagerCompat.INSTALL_SUCCEEDED;

public class MainFragment extends Fragment implements FoundPluginCallback, AdapterWrap.OnItemClickListener {
    private NovaRecyclerView list;
    private AdapterWrap<ApkItem> adapterWrap;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        list = (NovaRecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyAdapter adapter = new MyAdapter(getActivity());
        adapterWrap = new AdapterWrap<>(adapter);
        adapterWrap.addOnItemClickListener(this);

        list.setAdapter(adapterWrap);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DroidPluginFacade.startLoad(getActivity(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        DroidPluginFacade.onRequestPermissionsResult(requestCode, permissions, grantResults, getContext(), this);
    }

    @Override
    public void onFoundPlugin(ApkItem apkItem) {
        adapterWrap.add(apkItem);
    }

    @Override
    public void onItemClick(View view, int i) {
        final ApkItem apkItem = adapterWrap.getData(i);
        LoadingDialog.show(getContext());
        DroidPluginFacade.isInstalledAsync(apkItem, new QueryCallback() {
            @Override
            public void onQueryResult(boolean isSuccess) {
                if (isSuccess) {
                    Toast.makeText(getContext(), "已安装", Toast.LENGTH_SHORT).show();
                    LoadingDialog.dismiss();
                    DroidPluginFacade.actionStartPlugin(getContext(), apkItem);
                } else {
                    installPluginAndStart(apkItem);
                }
            }
        });
    }

    //安装并启动插件
    private void installPluginAndStart(final ApkItem apkItem) {
        DroidPluginFacade.installPlugin(apkItem, new InstallCallback() {
            @Override
            public void onInstallResult(int re) {
                LoadingDialog.dismiss();
                switch (re) {
                    case PluginManager.INSTALL_FAILED_NO_REQUESTEDPERMISSION:
                        Toast.makeText(getContext(), "安装失败，文件请求的权限太多", Toast.LENGTH_SHORT).show();
                        break;
                    case INSTALL_FAILED_NOT_SUPPORT_ABI:
                        Toast.makeText(getContext(), "宿主不支持插件的abi环境，可能宿主运行时为64位，但插件只支持32位", Toast.LENGTH_SHORT).show();
                        break;
                    case INSTALL_SUCCEEDED:
                        Toast.makeText(getContext(), "安装完成", Toast.LENGTH_SHORT).show();
                        DroidPluginFacade.actionStartPlugin(getContext(), apkItem);
                        break;
                }
            }
        });
    }
}
