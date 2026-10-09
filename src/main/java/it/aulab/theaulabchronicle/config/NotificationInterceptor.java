package it.aulab.theaulabchronicle.config;

import it.aulab.theaulabchronicle.repositories.ArticleRepository;
import it.aulab.theaulabchronicle.repositories.CareerRequestRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class NotificationInterceptor implements HandlerInterceptor {

    @Autowired
    private CareerRequestRepository careerRequestRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView != null && request.isUserInRole("ROLE_ADMIN")) {
            int careerCount = careerRequestRepository.findByStatus("NEW").size();
            modelAndView.addObject("careerRequests", careerCount);
        }

        if (modelAndView != null && request.isUserInRole("ROLE_REVISOR")) {
            int revisedCount = articleRepository.findByIsAcceptedIsNull().size();
            modelAndView.addObject("articlesToBeRevised", revisedCount);
        }
    }
}