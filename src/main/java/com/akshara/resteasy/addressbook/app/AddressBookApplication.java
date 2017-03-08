package com.akshara.resteasy.addressbook.app;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.akshara.resteasy.addressbook.AddressBook;

public class AddressBookApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public AddressBookApplication() {
		singletons.add(new AddressBook());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}



