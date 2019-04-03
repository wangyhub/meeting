package com.lw.common.ema.ea.sms.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ReceiveSMS extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4533035496103183087L;

	/**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    @SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        try {
            response.setContentType("text/xml; charset=UTF-8");
            out = response.getWriter();
            String postType = request.getParameter("postType");
            String eaStr = request.getParameter("ea");
            String account = request.getParameter("account");
            String smsMtMessage = request.getParameter("smsMoMessage");
            System.out.println("�յ���������͹��������У�postType=" + postType + ",ea=" + eaStr + ",account=" + account + ",smsMoMessage=" + smsMtMessage);
            Document doc = null;
            Document resDocument = DocumentHelper.createDocument();
            Element resRoot = resDocument.addElement("smsMoRes");
            try {
                resRoot.addElement("stat").setText("r:000");
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(resDocument.asXML());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }

        }

    }

}
