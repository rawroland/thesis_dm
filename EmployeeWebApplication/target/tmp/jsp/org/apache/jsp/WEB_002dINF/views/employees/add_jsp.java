package org.apache.jsp.WEB_002dINF.views.employees;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Distribution Manager|Add Employee</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h2>Add Employee</h2>\r\n");
      out.write("\t<form action=\"employees/add\" method=\"post\">\r\n");
      out.write("\t\t<label for=\"givenname\">First Name: </label>\r\n");
      out.write("\t\t<input id=\"givenname\" name=\"givenname\" type=\"text\" required=\"required\"><br>\r\n");
      out.write("\t\t<label for=\"surname\"> Surname: </label> \r\n");
      out.write("\t\t<input id=\"surname\" name=\"surname\" type=\"text\" required=\"required\"> <br>\r\n");
      out.write("\t\t<label for=\"username\"> Username: </label>\r\n");
      out.write("\t\t<input id=\"username\" name=\"username\" type=\"text\" required=\"required\"> <br>\r\n");
      out.write("\t\t<label for=\"role\"> Role: </label> \r\n");
      out.write("\t\t<select id=\"role\" name=\"role\" >\r\n");
      out.write("\t\t\t<option value=\"cashier\">cashier</option>\r\n");
      out.write("\t\t</select> <br>\r\n");
      out.write("\t\t<button type=\"submit\" name=\"add\" value=\"Add Employee\">Add Employee</button>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
