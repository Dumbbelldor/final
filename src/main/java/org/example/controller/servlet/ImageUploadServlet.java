package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.model.entity.User;
import org.example.model.entity.enumeration.Destination;
import org.example.model.service.UserService;
import org.example.model.service.impl.UserServiceImpl;
import org.example.model.util.helper.RequestHelper;
import org.example.model.util.helper.impl.RequestHelperImpl;
import org.example.model.validation.impl.FileValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * Servlet that bears responsibility of downloading and
 * applying users pictures from the profile page.
 */
@WebServlet(urlPatterns = "/upload_image")
@MultipartConfig(maxFileSize = 1024*8, maxRequestSize = 1024*8)
public class ImageUploadServlet extends HttpServlet {

    private static final String IMAGES_FOLDER = "/user_images";
    private static final String UPLOAD_PATH = "C:\\Users\\Max\\IdeaProjects\\Final\\src\\main\\webapp\\user_images";


    private static final FileValidator validator = FileValidator.INSTANCE;
    private static final RequestHelper helper = RequestHelperImpl.INSTANCE;
    private static final UserService service = UserServiceImpl.INSTANCE;

    /**
     * <p>Downloads an user's image and sets it as current
     * image of the profile if nothing was set.</p>
     *
     * <p>If downloaded file exists, deletes the downloaded
     * temporal file.</p>
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        helper.init(req, resp);

        String deleteRequest = req.getParameter(PARAM_DELETE_IMAGE);
        Part part = req.getPart(PARAM_PART_IMAGE);

        if (deleteRequest != null) {
            Long userId = Long.parseLong(deleteRequest);
            service.deleteImageById(userId);
        }

        if (part != null) {

            User user = (User) helper.getSessionAttribute(SESSION_CURRENT_USER);

            String fileName = part.getSubmittedFileName();
            String fullPath = UPLOAD_PATH + File.separator + fileName;
            if (validator.validateImage(fileName)) {

                if (!Files.exists(Path.of(fullPath))) {
                    part.write(fullPath);
                } else {
                    part.delete();
                }
                service.uploadImageById(IMAGES_FOLDER + File.separator + fileName,
                        user.getUserId());
            }
        }
        helper.redirect(Destination.GOTO_PROFILE);
    }
}
