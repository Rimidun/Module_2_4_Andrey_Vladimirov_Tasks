package project.servlets;

import com.google.gson.Gson;
import project.DTO.UserData;
import project.ObjectFactory;
import project.entity.File;
import project.service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UploadServlet extends HttpServlet {
    private MainService mainService;

    @Override
    public void init(){
        this.mainService = ObjectFactory.getObjectFactory().getMainService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();
        pw.println("Please, use post request.");
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        Gson gson = new Gson();
        UserData userData = gson.fromJson(br, UserData.class);
        if(userData == null){
            resp.setStatus(404);
            throw new Error("Don't worry, be happy!");
        }
        File file = mainService.upload(userData);
        if(file == null){
            resp.setStatus(404);
            throw new Error("Don't worry, be happy!");
        }

        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();
        String bodyText = "<div class=\"block1\">" +
                "File name: " + file.getFileName() + "\n" +
                "File size: " + file.getSize() + "\n" +
                "File status: " + file.getStatus() + "\n" +
                "File created: " + file.getCreated() + "\n" +
                "File last modified: " + file.getLastModified() + "\n" +
                "</div>\n";

        pw.println(HtmlParts.page("Upload page", bodyText));
        resp.setStatus(200);
    }
}
