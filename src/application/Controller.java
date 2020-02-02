package application;

import java.awt.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Formatter;
import org.omg.CORBA.PUBLIC_MEMBER;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller implements Initializable {
	ArrayList<altThread>altThreads=new ArrayList<altThread>();
	 controllerThread aaaControllerThread=new controllerThread();
	 subThreadCreator subCreator=new subThreadCreator();

	int as=0;
	@FXML
	ProgressIndicator pI1=new ProgressIndicator();
	@FXML
	ProgressIndicator pI2=new ProgressIndicator();
	@FXML
	ProgressIndicator pI3=new ProgressIndicator();
	@FXML
	ProgressIndicator pI4=new ProgressIndicator();
	@FXML
	ProgressIndicator pI5=new ProgressIndicator();
	@FXML
	ProgressIndicator pI6=new ProgressIndicator();
	@FXML
	ProgressIndicator pI7=new ProgressIndicator();
	@FXML
	ProgressIndicator pI8=new ProgressIndicator();
	@FXML
	ProgressIndicator pI9=new ProgressIndicator();
	@FXML
	ProgressIndicator pI10=new ProgressIndicator();
	@FXML
	ProgressBar mainThreadPb=new ProgressBar();
	@FXML
	ProgressBar asd=new ProgressBar();
	@FXML
	ProgressBar asd1=new ProgressBar();
	@FXML
	ProgressBar asd2=new ProgressBar();
	@FXML
	ProgressBar asd3=new ProgressBar();
	@FXML
	ProgressBar asd4=new ProgressBar();
	@FXML
	ProgressBar asd5=new ProgressBar();
	@FXML
	ProgressBar asd6=new ProgressBar();
	@FXML
	ProgressBar asd7=new ProgressBar();
	@FXML
	ProgressBar asd8=new ProgressBar();
	static ExecutorService executer= Executors.newScheduledThreadPool(20);
	int Sayac=1;

	public static int AnaSunucu_istek=0;

	 class mainThread extends Thread {
			public void run() {
				Thread getValueThread =new Thread(){
					public synchronized void run() {
						while (true) {
							Random random=new Random();
							int tmp=500;
							while(AnaSunucu_istek+tmp>10000) {
								tmp=random.nextInt(101);
							}
							AnaSunucu_istek+=tmp;
							if(AnaSunucu_istek>=0) {
								//System.out.println(AnaSunucu_istek);
								mainThreadPb.setProgress((float)AnaSunucu_istek/10000);
								//pI1.setProgress((float)AnaSunucu_istek/10000);
								
							}
							
							//System.out.println(AnaSunucu_istek);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}	
				};getValueThread.start();
				Thread replyThread =new Thread(){
					public synchronized void run() {
						while (true) {
							Random random=new Random();
							int tmp=random.nextInt(51);
							while(AnaSunucu_istek-tmp<0) {
								tmp=random.nextInt(51);
							}
							AnaSunucu_istek-=tmp;
							if(AnaSunucu_istek<0) {
								AnaSunucu_istek=0;
							}
						
							//System.out.println(AnaSunucu_istek);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				};replyThread.start();
			}
			
		}
	 class altThread extends Thread {

		 private int  altThreadValue=0;
		 private int cntrl;
		 private boolean ctrl=true;
		 public altThread(int cntrl){
			 this.cntrl=cntrl;
		 }
			public void run() {
				Thread getValueThread =new Thread(){
					public synchronized void run() {
						int a=Sayac;
						while (ctrl) {
							Random random=new Random();
							int tmp=250;
							if(AnaSunucu_istek>0) {
							while(AnaSunucu_istek-tmp<0) {
								tmp=random.nextInt(51);
							}
							AnaSunucu_istek-=tmp;
							if(AnaSunucu_istek<=0) {
								AnaSunucu_istek=0;
							}
							altThreadValue+=tmp;
							}
							//System.out.println("Thread"+a+"	"+AnaSunucu_istek+"	"+r);
							try {
								Thread.sleep(100);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				};getValueThread.start();
				Thread replyThread =new Thread(){
					public synchronized void run() {
						while (ctrl) {
							Random random=new Random();
							int tmp=random.nextInt(51);
							while(altThreadValue-tmp<0) {
								tmp=random.nextInt(51);
							}
							altThreadValue-=tmp;
						
							//System.out.println(AnaSunucu_istek+"	"+r);
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				};replyThread.start();
			}
			public int getaltThreadValue() {
				return altThreadValue;
			}
			public void setaltThreadValue(int r) {
				this.altThreadValue=r;
			}
			public int getCntrl() {
				return cntrl;
			}
			public void setCtrl(boolean ctrl) {
				this.ctrl=ctrl;
			}
		}

	 class controllerThread extends Thread {
		 
		 public synchronized void run() {
			 
				while (true) {
					System.out.print("Thread Sayisi:"+altThreads.size()+"	Ana sunucu: %"+String.format("%.02f",((float)AnaSunucu_istek/10000*100))+"\t");
					for (int i = 0; i < altThreads.size(); i++) {
						int x=altThreads.get(i).getCntrl();
						System.out.print("Thread"+(altThreads.get(i).getCntrl())+": %"+String.format("%.02f",((float)altThreads.get(i).getaltThreadValue()/5000*100))+"\t");
						if(x==0) {
							asd.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI2.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}else if(x==1) {
							asd1.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI3.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}else if(x==2) {
							asd2.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI4.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}else if(x==3) {
							asd3.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI5.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}else if(x==4) {
							asd4.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI6.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}else if(x==5) {
							asd5.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI7.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}else if(x==6) {
							asd6.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI8.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}else if(x==7) {
							asd7.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI9.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}else if(x==8) {
							asd8.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
							//pI10.setProgress((float)altThreads.get(i).getaltThreadValue()/5000);
						}				
					}
					System.out.println();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		 }
	 }
    class subThreadCreator extends Thread{
    	
    	
		@SuppressWarnings("deprecation")
		public synchronized void run() {
   		 
		 mainThread aaThread=new mainThread();
			altThread alThread=new altThread(0);
			altThreads.add(alThread);
			as++;
			altThread al1Thread=new altThread(as);
			altThreads.add(al1Thread);
			
			as++;
			
		//	altThreads.get(0).setPriority(MAX_PRIORITY);
			executer.execute(altThreads.get(0));
			executer.execute(altThreads.get(1));
			executer.execute(aaThread);
    		while(true) {
    	
    		for (int i = 0; i < altThreads.size(); i++) {
    		if((float)altThreads.get(i).getaltThreadValue()/5000>0.7) {
				System.out.println("Thread"+(altThreads.get(i).getCntrl()+1)+" Deger: "+altThreads.get(i).getaltThreadValue()+" Thread "+(as+1)+" calismaya basladi");
				altThreads.add(new altThread(as));
				altThreads.get(as).setaltThreadValue(altThreads.get(i).getaltThreadValue()/2);
				altThreads.get(i).setaltThreadValue(altThreads.get(i).getaltThreadValue()/2);
				
				executer.execute(altThreads.get(as));
				as++;
				
			}if(altThreads.get(i).getaltThreadValue()==0&&altThreads.get(i).getCntrl()!=0&&altThreads.get(i).getCntrl()!=1) {
				System.out.println("Thread"+(altThreads.get(i).getCntrl()+1)+" durduruldu");
				altThreads.get(i).setCtrl(false);
				altThreads.remove(i);
				
				//altThreads.get(i).altThreadValue=1;
				as--;
			}
    		}
    		}
    	}
    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		 executer.execute(subCreator);
		 executer.execute(aaaControllerThread);
		 
	
	
	}
	}