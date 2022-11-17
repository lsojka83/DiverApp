<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- imports function tags from JSTL, prefix "fn"--%>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD Porad</h1>
        <a href="/admin/addeditadvice" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i>Dodaj porade</a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive-lg">

                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nazwa</th>
                        <th>Treść</th>
                        <th>Kategoria</th>
                        <th>Obrazek</th>
                        <th>Film</th>
                        <th>Pytania</th>
                        <th>Ocena całkowita</th>
                        <th>Data utworzenia</th>
                        <th>Data modyfikacji</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="advice" items="${advices}">
                        <tr role="row" class="odd">
                            <td class="sorting_1">${advice.id}</td>
                            <td>${advice.name}</td>
                            <td>
                            ${fn:substring(advice.adviceText, 0, 40)}...
                            </td>
                            <td>${advice.category.name}</td>
                            <td>${advice.image.name}</td>
                            <td>${advice.movie.name}</td>
                            <td>
                            <c:forEach var="question" items="${advice.quizQuestions}">
                            ${question.questionText}
                            </c:forEach>
                            </td>
                            <td>${advice.rating}</td>
                            <td>${advice.createdOn}</td>
                            <td>${advice.lastModifiedOn}</td>
                            <td>
                                <a href="/admin/addeditadvice?id=${advice.id}">Edytuj</a>
                                <a href="/admin/deleteadvice?id=${advice.id}" onclick="return confirm('Czy skasować?')">Skasuj</a>
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