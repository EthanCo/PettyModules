# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Java\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontwarn okio.**
-keep class okio.** { *;}

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}

-dontwarn com.ximalaya.ting.android.player.**
-keep class com.ximalaya.ting.android.player.** { *;}

-dontwarn com.google.gson.**
-keep class com.google.gson.** { *;}

-dontwarn android.support.**
-keep class android.support.** { *;}

-keep interface com.ximalaya.ting.android.opensdk.** {*;}
-keep class com.ximalaya.ting.android.opensdk.** { *; }
