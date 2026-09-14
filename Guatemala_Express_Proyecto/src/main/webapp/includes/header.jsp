<%-- 
    Document   : nav-bar
    Created on : Sep 7, 2026, 12:13:33 PM
    Author     : matul
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<header>
    <nav class="bg-white shadow-md">
        <div class="pr-30 pl-10">
            <ul class="flex h-15 items-center justify-between">

                <li class="flex items-center">
                    <a
                        href="${pageContext.request.contextPath}/"
                        class="text-xl font-bold text-slate-900"
                    >
                        <span class="text-slate-900">Guatemala</span>
                        <span class="text-sky-700">Express</span>
                    </a>
                </li>

                <c:forEach items="${requestScope.menuItems}" var="item">
                    <li class="group relative flex h-5 items-center">
                        <c:url var="itemUrl" value="${item.url}"/>

                        <a
                            href="${itemUrl}"
                            class="ml-4 text-sm font-medium text-gray-700
                                   transition-colors hover:text-sky-700"
                        >
                            <c:out value="${item.label}"/>

                            <c:if test="${not empty item.subOpciones}">
                                <i
                                    class="pi pi-angle-down text-xs
                                           transition-transform duration-300
                                           group-hover:rotate-180"
                                ></i>
                            </c:if>
                        </a>

                        <c:if test="${not empty item.subOpciones}">
                            <ul
                                class="invisible absolute left-0 top-full z-50
                                       min-w-48 list-none rounded-lg border
                                       border-gray-200 bg-white p-0 opacity-0
                                       shadow-md transition-all duration-300
                                       group-hover:visible group-hover:opacity-100"
                            >
                                <c:forEach items="${item.subOpciones}" var="subOpcion">
                                    <c:url var="subOpcionUrl" value="${subOpcion.url}"/>

                                    <li>
                                        <a
                                            href="${subOpcionUrl}"
                                            class="block px-4 py-2 font-thin
                                                   text-gray-700 transition
                                                   hover:bg-gray-100
                                                   hover:text-gray-900"
                                        >
                                            <c:out value="${subOpcion.label}"/>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>

                    </li>
                </c:forEach>

            </ul>
        </div>
    </nav>
</header>