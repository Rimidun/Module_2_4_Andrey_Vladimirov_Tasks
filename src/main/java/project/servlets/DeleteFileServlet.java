package project.servlets;

import javassist.NotFoundException;
import project.ObjectFactory;
import project.service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteFileServlet extends HttpServlet {
    private MainService mainService;

    @Override
    public void init() throws ServletException {
        this.mainService = ObjectFactory.getObjectFactory().getMainService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        Long userId;
        String fileName;
        try{
            userId = Long.parseLong(req.getParameter("userid"));
            fileName = req.getParameter("filename");
        }catch (NumberFormatException e){
            resp.setStatus(502);
            throw new Error("Not valid id on parse");
        }

        try {
            mainService.removeFile(userId, fileName);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        pw.println(HtmlParts.page("Remove file page", "File is " + fileName + " removed"));
        resp.setStatus(200);
    }
}