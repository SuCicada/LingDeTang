package org.subbs.util.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/18/19
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SSHConnection {
    //前两条用来连接免密码登录远程服务器的,我这里用的是密码登录,所以注释掉
    private final static String S_PATH_FILE_PRIVATE_KEY = "/Users/.ssh/id_rsa";
    private final static String S_PATH_FILE_KNOWN_HOSTS = "/Users/.ssh/known_hosts";
    private final static String S_PASS_PHRASE = "";
    private final static int LOCAl_PORT = 3307;//本地数据库配置地址,可以随意更换,但是注意不要和别的端口冲突
    private final static int REMOTE_PORT = 3306;//远程服务器数据库地址,我这里连接的是postgresql数据库,如果是mysql的话就是3306
    private final static int SSH_REMOTE_PORT = 22;
    private final static String SSH_USER = "lingde";
    private final static String SSH_PASSWORD = "tang";
    private final static String SSH_REMOTE_SERVER = "sucicada.tk";
    private final static String MYSQL_REMOTE_SERVER = "localhost";

    private Session sesion; //represents each ssh session

    public void closeSSH ()
    {
        sesion.disconnect();
    }

    public SSHConnection () throws Throwable
    {
        JSch jsch = null;

        jsch = new JSch();
        //jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
        //jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY);

        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);

        sesion.setPassword(SSH_PASSWORD);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        sesion.setConfig(config);

        sesion.connect(); //ssh connection established!
        System.out.println("ssh connection established!");
        //by security policy, you must connect through a fowarded port
        sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT);

    }
}