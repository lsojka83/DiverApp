<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD Roli</h1>
        <a href="/admin/addeditrole" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i>Dodaj rolę</a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista</h6>
        </div>
               <c:if test="${answerUsed=='yes'}">
                     <div class="card-header py-3">
                            <h2 class="m-0 font-weight-bold text-primary">Rola używana, nie można skasować</h2>
                        </div>
                  </c:if>

        <div class="card-body">
            <div class="table-responsive-lg">

                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nazwa</th>
                        <th>Data utworzenia</th>
                        <th>Data modyfikacji</th>
                        <th>Akcje</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="role" items="${roles}">
                        <tr role="row" class="odd">
                            <td class="sorting_1">${role.id}</td>
                            <td>${role.name}</td>
                            <td>${role.createdOn}</td>
                            <td>${role.lastModifiedOn}</td>
                            <td>
                                <a href="/admin/addeditrole?id=${role.id}">Edytuj</a>
                                <a href="/admin/deleterole?id=${role.id}" onclick="return confirm('Czy skasować?')">Skasuj</a>
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