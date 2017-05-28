package com.apache.encryptor;

import java.util.concurrent.locks.ReentrantLock;

public class SingletonLockManager{
	
    private static class Builder{
        private static final ReentrantLock lock = new ReentrantLock();
    }

    public static ReentrantLock instance(){
         return Builder.lock;
    }
    
}