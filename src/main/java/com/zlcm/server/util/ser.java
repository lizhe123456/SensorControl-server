package com.zlcm.server.util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ser {

    public static void main(String[] args){
        ServerSocket serversocket=null;
        Socket socket=null;
        BufferedReader br=null;
        PrintWriter pw=null;
        Scanner sc=null;
        try{
            serversocket=new ServerSocket(9023);
            socket=serversocket.accept();
            System.out.println("client connected!");
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            sc=new Scanner(System.in);
            while(true){
                String str=br.readLine();
                if("END".equals(str)){
                    pw.println("END");
                    pw.flush();
                    break;
                }else{
                    System.out.println("client:"+str);
                    str=sc.nextLine();
                    pw.println(str);
                    pw.flush();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                System.out.println("closed......");
                br.close();
                pw.close();
                socket.close();
                serversocket.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
