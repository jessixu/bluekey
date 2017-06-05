package BlueKey;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;


public class Mail {
	private static String  mailServer = "smtp.163.com";  			//�����������
	private static String  mailProt = "smtp";						//�������Э��
	
	private static String  mailAccount = "15521328115@163.com";		//�����������
	private static String  mailPassword = "wangyiwl092";
	private static String  receiveMailAccount = "598721443@qq.com";
	
//	public void sendMail(String context, String receiveMailAccount) throws Exception{
	public static void main(String[] args) throws Exception {
		try{// 1. ����һ���ʼ�
		    Properties props = new Properties();               
		    props.setProperty("mail.debug", "true");			   	//����debug����ģʽ
		    props.setProperty("mail.host", mailServer);   		   	//�����ʼ���������������
		    props.setProperty("mail.transport.protocol", mailProt);	//�����ʼ���������Э������
		    props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤

		    
		    // PS: ĳЩ���������Ҫ�� SMTP ������Ҫʹ�� SSL ��ȫ��֤ (Ϊ����߰�ȫ��, ����֧��SSL����, Ҳ�����Լ�����),
	        //     ����޷������ʼ�������, ��ϸ�鿴����̨��ӡ�� log, ����������� ������ʧ��, Ҫ�� SSL ��ȫ���ӡ� �ȴ���,
	        //     ������ /* ... */ ֮���ע�ʹ���, ���� SSL ��ȫ���ӡ�
	        
	        // SMTP �������Ķ˿� (�� SSL ���ӵĶ˿�һ��Ĭ��Ϊ 25, ���Բ����, ��������� SSL ����,
	        //                  ��Ҫ��Ϊ��Ӧ����� SMTP �������Ķ˿�, ����ɲ鿴��Ӧ�������İ���,
	        //                  QQ�����SMTP(SLL)�˿�Ϊ465��587, ������������ȥ�鿴)
//	        final String smtpPort = "465";
//	        props.setProperty("mail.smtp.port", smtpPort);
//            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//	        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//	        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
	        
		    
		    Session session= Session.getDefaultInstance(props); // ���ݲ������ã������Ự����Ϊ�˷����ʼ�׼���ģ�
		    
		    MimeMessage message = createMimeMessage(session, mailAccount, receiveMailAccount);

		    Transport transport = session.getTransport();
		    transport.connect(mailServer,mailPassword);
		    transport.sendMessage(message, message.getAllRecipients());
		    
		    transport.close();
		}
		catch (Exception e) {
		      System.err.println("�ʼ�����ʧ�ܵ�ԭ���ǣ�" + e.getMessage());
		      System.err.println("�������ԭ��");
		      e.printStackTrace(System.err);
		    }
	}
	
	/**
     * ����һ��ֻ�����ı��ļ��ʼ�
     *
     * @param session �ͷ����������ĻỰ
     * @param sendMail ����������
     * @param receiveMail �ռ�������
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. ����һ���ʼ�
        MimeMessage message = new MimeMessage(session);

        // 2. From: ������
        message.setFrom(new InternetAddress(sendMail, "ĳ����", "UTF-8"));

        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX�û�", "UTF-8"));

        // 4. Subject: �ʼ�����
        message.setSubject("�����һ�", "UTF-8");

        // 5. Content: �ʼ����ģ�����ʹ��html��ǩ��
        message.setContent("XX�û����, ����ȫ��5��, ��������, ��������ٵ�һ�ꡣ����", "text/html;charset=UTF-8");

        // 6. ���÷���ʱ��
        message.setSentDate(new Date());

        // 7. ��������
        message.saveChanges();

        return message;
    }

	 
}
