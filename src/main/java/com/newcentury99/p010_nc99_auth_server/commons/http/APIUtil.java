package com.newcentury99.p010_nc99_auth_server.commons.http;

import com.newcentury99.p010_nc99_auth_server.commons.ObjectMapperUtil;
import com.newcentury99.p010_nc99_auth_server.library_temp.LibraryAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newcentury99.p010_nc99_auth_server.library_temp.messages.SDKError;
import feign.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;

public class APIUtil {
    private static final Logger logger = LogManager.getLogger();
    private static final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @LibraryAPI
    public static <T extends GeneralResDTO> ResponseEntity<?> executeAPI(ThrowableSupplier<T> procedure, String successRawMsg) {
        try {
            T resDTO = procedure.get();
            resDTO.set_metadata(new DTOMetadata(successRawMsg));
            return ResponseEntity.ok(resDTO);
        } catch (Exception e) {
            GeneralResDTO errorResDTO = new GeneralResDTO();
            APIUtil.printErrorLog(e);
            errorResDTO.set_metadata(new DTOMetadata(e.getMessage(), e.getClass().getName()));
            return ResponseEntity.status(400).body(errorResDTO);
        }
    }

    @LibraryAPI
    public static void executeCSVExportAPI(ThrowableSupplier<byte[]> procedure, HttpServletResponse response, String fileName) {
        try {
            byte[] resource = procedure.get();
            String contentDispositionHeaderContent = "attachment; filename=\"" + fileName + LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("_yyyyMMdd_hhmm")) + ".csv\"";

            response.setContentType("application/octet-stream");
            response.setContentLength(resource.length);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDispositionHeaderContent);
            response.setHeader(HttpHeaders.TRANSFER_ENCODING, "binary");
            response.getOutputStream().write(resource);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            GeneralResDTO errorResDTO = new GeneralResDTO();
            APIUtil.printErrorLog(e);
            errorResDTO.set_metadata(new DTOMetadata(e.getMessage(), e.getClass().getName()));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            try {
                response.getWriter().write(objectMapper.writeValueAsString(errorResDTO));
            } catch (Exception ignored) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/plain");
                response.setContentLength(0);
            }
        }
    }

    // Pageable Response Generation API
    @LibraryAPI
    public static <T extends GeneralPageableResDTO, E> void buildPageableResDTO(T resDTO, Page<E> page, Consumer<List<E>> contentSetter) {
        if (page.getPageable().isPaged()) {
            resDTO.setPageable(true);
            resDTO.setPageIdx(page.getNumber());
            resDTO.setTotalPage(page.getTotalPages());
            resDTO.setPageElementSize(page.getTotalElements());
        }
        contentSetter.accept(page.getContent());
    }

    // Low-Level Restful Response APIs
    @LibraryAPI
    public static String getBodyJSONString(InputStream inputStream) throws IOException {
        return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    }

    @LibraryAPI
    public static <T> T getBodyJSON(InputStream inputStream, Class<T> cls) throws IOException {
        return ObjectMapperUtil.parseFromJSONString(getBodyJSONString(inputStream), cls);
    }

    /**
     * Serialize restful API response DTO object
     * @param writer JSON text writer
     * @param resDTO HTTP Response DTO to convert to JSON string
     * @throws IOException Exception will raise when writer is not able to print JSON
     */
    @LibraryAPI
    public static void toBodyJSON(PrintWriter writer, Object resDTO) throws IOException {
        writer.write(objectMapper.writeValueAsString(resDTO));
    }

    // MSA Request API
    /**
     * Execute MSA Inter-service API Request call
     * @param api MSA Inter-service API execution entrypoint method.
     * @param cls Class instance of response DTO class
     * @return Inter-service API call response
     * @param <T> Type of response DTO class
     * @throws Exception Inter-service API call exception
     */
    @LibraryAPI
    public static <T> T reqMsaAPICall(ThrowableSupplier<Response> api, Class<T> cls) throws Exception {
        try (Response response = api.get()) {
            return APIUtil.getBodyJSON(response.body().asInputStream(), cls);
        } catch (IOException e) {
            throw new IllegalStateException(SDKError.ERROR_REQUEST_OTHER_MS_JSON_NOT_PARSEABLE.getMessage());
        }
    }

    // Internal Utility Methods
    /**
     * Get stacktrace string of the error obj.
     * @param e Exception object of the error
     * @return Stacktrace multi-line strings
     */
    private static String getPrintStackTrace(Exception e) {
        StringWriter errorLogs = new StringWriter();
        e.printStackTrace(new PrintWriter(errorLogs));
        return errorLogs.toString();
    }

    /**
     * Print error log to the console.
     * @param e Exception object of the error.
     */
    private static void printErrorLog(Exception e) {
        logger.warn(e.getLocalizedMessage());
        logger.warn("------------------------------ Request Process Failed | StackTrace ------------------------------");
        logger.warn(APIUtil.getPrintStackTrace(e));
        logger.warn("-------------------------------------------------------------------------------------------------");
    }
}
