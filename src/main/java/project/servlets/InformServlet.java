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
import java.util.List;

public class InformServlet extends HttpServlet {
    private MainService mainService;

    public void init() {
        this.mainService = ObjectFactory.getObjectFactory().getMainService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.setStatus(404);
            throw new Error("Ошибка id");
        }

        List<File> files = mainService.getFileLists(id);
        if (files.size() == 0 || files == null) {
            pw.println(HtmlParts.page("Files on user", "Files not found"));
            resp.setStatus(404);
        }

        StringBuilder content = new StringBuilder();
        for (File file : files) {
            content.append("File: ").append(file.getFileName()).append("<br>")
                    .append("Name: ").append(file.getName()).append("<br>")
                    .append("Type: ").append(file.getType()).append("<br>")
                    .append("Size: ").append(file.getSize()).append("<br>")
                    .append("Status: ").append(file.getStatus()).append("<br>")
                    .append("Created: ").append(file.getCreated()).append("<br>")
                    .append("Modified: ").append(file.getLastModified()).append("<br>")
                    .append("<hr>").append("<br>");
        }

        pw.println(HtmlParts.page("Files on user", content.toString()));
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
