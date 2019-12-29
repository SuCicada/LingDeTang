package org.subbs.util;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理PUT提交参数(只对文件表单生效)
 * Created by Hy on 2018/9/30.
 */
public class PostAndPutCommonsMultipartResolver extends CommonsMultipartResolver {

    @Override
    public boolean isMultipart(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
            return FileUploadBase.isMultipartContent(new ServletRequestContext(request));
        }
        return false;
    }

}