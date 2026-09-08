<%-- 
    Document   : nav-bar
    Created on : Sep 7, 2026, 12:13:33 PM
    Author     : matul
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.guatemala_express_proyecto.modelos.MenuItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header> 
    <nav class="bg-white shadow-md">
        <div class="pr-30 pl-10">
            <ul class="flex justify-between h-15 items-center">
                <li class="flex items-center">
                    <a href="${pageContext.request.contextPath}/" class="text-xl font-bold text-slate-900">
                        <div>
                            <span class="text-slate-900">Guatemala</span>
                            <span class="text-sky-700">Express</span>
                        </div>
                    </a>
                </li>

            <%  List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
                    if (menuItems != null) {
                        for (MenuItem item : menuItems) { 
                            boolean tieneSub = item.getSubOpciones() != null && !item.getSubOpciones().isEmpty(); %>
                            <li class="flex items-center group relative h-5">
                                <a href="${pageContext.request.contextPath}<%= item.getUrl() %>" 
                                class="ml-4 text-sm font-medium text-gray-700 hover:text-sky-700">
                                <%= item.getLabel() %>
                            <%  if(tieneSub) { %>
                                    <i class=" pi pi-angle-down text-xm transition-transform duration-300 group-hover:rotate-180"></i>
                            <%  } %>
                                </a>
                            <% 
                                if(tieneSub) { 
                            %>
                                <ul class="absolute left-0 top-full z-50 min-w-48 list-none rounded-lg border border-gray-200 bg-white p-0 opacity-0 shadow-md transition-all duration-300 group-hover:opacity-100 group-hover:visible invisible">
                                    <%  
                                        for (MenuItem subOpcion: item.getSubOpciones()) { 
                                    %>
                                            <li>
                                                <a
                                                    href="${pageContext.request.contextPath}<%= subOpcion.getUrl() %>"
                                                    class="block px-4 py-2 font-thin text-gray-700 transition hover:bg-gray-100 hover:text-gray-900"
                                                >
                                                    <%= subOpcion.getLabel() %>
                                                </a>
                                            </li>
                                    <%  } %>
                                </ul>
                            <%  } %>   
                            </li>
                    <%  } %>
                <%  } %>
            </ul>
        </div>
    </nav>
</header>