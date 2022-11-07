package io.captyup.resource

import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import io.captyup.dto.FileUploadInput
import org.jboss.resteasy.reactive.MultipartForm
import org.jboss.resteasy.reactive.multipart.FileUpload
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/file")
class FileResource {
    @Inject
    lateinit var storage: Storage

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    fun upload(@MultipartForm input: FileUploadInput): Response {
        val file: FileUpload = input.file
        val blobInfo = BlobInfo.newBuilder(
            "myBucket",
            file.fileName()
        ).setContentType(file.contentType()).build()
        storage.createFrom(
            blobInfo,
            file.uploadedFile(),
            Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PRIVATE)
        )
        return Response.ok().build()
    }
}
