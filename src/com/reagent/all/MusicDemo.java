package com.reagent.all;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MusicDemo {
	public MusicDemo(){
		URL musicURL = null;
		try{
			File f=new File("music/3.wav"); //将文件的地址转为URL
			musicURL=f.toURL();
			musicURL = new URL("myMusic.wmv");
		}catch(Exception e){
			//e.printStackTrace();
		}
		AudioClip ac = Applet.newAudioClip(musicURL); //得到一个播放音频的实例
		ac.play();//播放一编
		//ac.loop();//循环播放
		//ac.stop();//停止
	}
}