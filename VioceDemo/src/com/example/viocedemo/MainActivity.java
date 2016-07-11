package com.example.viocedemo;

import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechListener;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUser;
import com.iflytek.cloud.speech.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener,SynthesizerListener{
	private EditText editText;
	private Button button1;
	private Button button2;
	//�ϳɶ���
	private SpeechSynthesizer speechSynthesizer;
	//ʶ�𴰿�
	private RecognizerDialog recognizerDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//appid�����Լ������
		SpeechUser.getUser().login(MainActivity.this, null, null, "appid=12345678", listener);
		init();
		setParam();
		
	}
	
	public void init()
	{
		editText = (EditText) findViewById(R.id.editText1);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
	}
	
	public void setParam()
	{
		speechSynthesizer = SpeechSynthesizer.createSynthesizer(this);
		speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
		speechSynthesizer.setParameter(SpeechConstant.SPEED, "50");
		speechSynthesizer.setParameter(SpeechConstant.VOLUME, "50");
		speechSynthesizer.setParameter(SpeechConstant.PITCH, "50");
	}
	
	public void setDialog()
	{
		recognizerDialog = new RecognizerDialog(this);
		recognizerDialog.setParameter(SpeechConstant.DOMAIN, "iat");
		recognizerDialog.setParameter(SpeechConstant.SAMPLE_RATE, "16000");
		editText.setText(null);
		//��ʾDialog
		recognizerDialog.setListener(dialogListener);
		recognizerDialog.show();
	}
	
	/**
	 * ʶ��ص�������
	 */
	private RecognizerDialogListener dialogListener = new RecognizerDialogListener() {
		//ʶ�����ص�
		@Override
		public void onResult(RecognizerResult arg0, boolean arg1) {
			// TODO Auto-generated method stub
			String text = JsonParser.parseIatResult(arg0.getResultString());
			editText.append(text);
			editText.setSelection(editText.length());
		}
		
		//ʶ������ص�
		@Override
		public void onError(SpeechError arg0) {
			// TODO Auto-generated method stub
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			String text = editText.getText().toString();
			speechSynthesizer.startSpeaking(text, this);
			break;
		case R.id.button2:
			setDialog();
			break;
		default:
			break;
		}
	}
	
	//������Ȼص�֪ͨ
	@Override
	public void onBufferProgress(int arg0, int arg1, int arg2, String arg3) {
		// TODO Auto-generated method stub
		
	}
	
	//�����ص�
	@Override
	public void onCompleted(SpeechError arg0) {
		// TODO Auto-generated method stub
		
	}
	//��ʼ����
	@Override
	public void onSpeakBegin() {
		// TODO Auto-generated method stub
		
	}
	//��ͣ����
	@Override
	public void onSpeakPaused() {
		// TODO Auto-generated method stub
		
	}
	//���Ž���
	@Override
	public void onSpeakProgress(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	//��������
	@Override
	public void onSpeakResumed() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * ͨ�ûص��ӿ�
	 */
	private SpeechListener listener = new SpeechListener() {
		
		//��Ϣ�ص�
		@Override
		public void onEvent(int arg0, Bundle arg1) {
			// TODO Auto-generated method stub
			
		}
		
		//���ݻص�
		@Override
		public void onData(byte[] arg0) {
			// TODO Auto-generated method stub
			
		}
		
		//�����ص���û�д���
		@Override
		public void onCompleted(SpeechError arg0) {
			// TODO Auto-generated method stub
			
		}
	};

}
