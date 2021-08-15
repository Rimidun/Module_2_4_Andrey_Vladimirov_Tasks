package project.servlets;

import project.ObjectFactory;
import project.entity.File;
import project.service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadServlet extends HttpServlet {
    private MainService mainService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        Long id = Long.parseLong(req.getParameter("id"));
        String fileName = req.getParameter("filename");
        String downloadPath = req.getParameter("path");

        File find = mainService.getFileLists(id).stream().filter(f -> f.getFileName().equals(fileName)).findFirst().orElse(null);
        if(find == null){
            resp.setStatus(502);
            throw new Error("File is " + fileName + " not found");
        }

        mainService.downloadFile(fileName, downloadPath);
        pw.println(HtmlParts.page("Download file page", "Download completed."));
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        this.mainService = ObjectFactory.getObjectFactory().getMainService();
    }
}
