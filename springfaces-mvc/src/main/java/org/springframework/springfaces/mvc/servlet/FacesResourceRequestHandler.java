/*
 * Copyright 2010-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.springfaces.mvc.servlet;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.springfaces.mvc.context.SpringFacesContext;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * {@link HttpRequestHandler} that delegate JSF resource requests to the {@link ResourceHandler}.
 * 
 * @author Phillip Webb
 */
public class FacesResourceRequestHandler extends WebApplicationObjectSupport implements HttpRequestHandler {

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		SpringFacesContext springFacesContext = SpringFacesContext.getCurrentInstance(false);
		if (springFacesContext != null) {
			FacesContext facesContext = springFacesContext.getFacesContext();
			try {
				ResourceHandler resourceHandler = facesContext.getApplication().getResourceHandler();
				resourceHandler.handleResourceRequest(facesContext);
			} finally {
				facesContext.release();
			}
		}
	}

}
