package com.lib.frame.injector;

public interface AbstractInjector<T>
{
	void inject(T target, Object source);
}
