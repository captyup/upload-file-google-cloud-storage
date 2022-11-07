package io.captyup.dto

import org.jboss.resteasy.reactive.multipart.FileUpload
import javax.ws.rs.FormParam

class FileUploadInput {
    @FormParam("file")
    lateinit var file: FileUpload
}
