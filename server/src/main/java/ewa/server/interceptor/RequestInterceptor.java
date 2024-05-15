package ewa.server.interceptor;

import ewa.server.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //Get all headers
        Map<String, List<String>> headersMap = Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(request.getHeaders(h))
                ));

        //Try to verify the token and return username
        try {
            if(!headersMap.containsKey("authorization")){
                throw new Exception("No token found");
            }

            String token = headersMap.get("authorization").toString().replace("[", "").replace("]", "").replace("Bearer ", "").trim();
            request.setAttribute("username", JwtUtil.verifyToken(token));

            return true;
        } catch (Exception e){
            ObjectMapper mapper = new ObjectMapper();
            String msg = e.getMessage();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(mapper.writeValueAsString(msg));
            return false;
        }
    }
}
