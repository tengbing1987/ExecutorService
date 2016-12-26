package com.bham.createThread;

public class MyThread implements Runnable {  
	
	public static void main(String[] args){
		MyThread myThread = new MyThread();  
	    Thread thread = new Thread(myThread);  
	    thread.start();  
	}
	
    @Override
    public void run() {  
		System.out.println("MyThread.run()");  
	}  
} 
	
