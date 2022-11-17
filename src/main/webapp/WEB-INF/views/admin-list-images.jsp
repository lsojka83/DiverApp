<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD Obrazów</h1>
        <a href="/admin/addeditimage" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i>Dodaj obraz</a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista</h6>
            <h6 class="m-0 font-weight-bold text-primary">Folder zapisu plików: ${imagesFolder}</h6>
            <br>
            <!--  ${pageContext.request.contextPath} -->
           ${pageContext.request.requestURL}
            <!-- !!! ${request.param} -->
            <br>
           !!! ${url}
        </div>
        <div class="card-body">
            <div class="table-responsive-lg">

                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nazwa</th>
                        <th>Link</th>
                        <th>Podgląd</th>
                        <th>Data utworzenia</th>
                        <th>Data modyfikacji</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="image" items="${images}">
                        <tr role="row" class="odd">
                            <td class="sorting_1">${image.id}</td>
                            <td>${image.name}</td>
                            <td>${image.link}</td>
                             <td>
                              <c:if test="${not empty image.link}">

                               <a href="${url}/${image.link}.jpg">

<%--
                               <a href="${url}${image.link}">
--%>
                             <img src="/images/${image.link}.jpg" width="100" height="80" alt=${image.name}>
<%--
                             <img src="/image?uuid=${image.link}" width="100" height="80" alt=${image.name}>
--%>
                              </c:if>

                             </td>
                            <td>${image.createdOn}</td>
                            <td>${image.lastModifiedOn}</td>
                            <td>
                                <a href="/admin/addeditimage?id=${image.id}">Edytuj</a>
                                <a href="/admin/deleteimage?id=${image.id}" onclick="return confirm('Czy skasować?')">Skasuj</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<%@ include file="/WEB-INF/views/jspf/admin-footer.jspf" %>