<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD Pytań</h1>
        <a href="/admin/addeditquizquestion" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i>Dodaj pytanie</a>
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
                        <th>Treść</th>
                        <th>Kategoria</th>
                        <th>Poprawna odpowiedz</th>
                        <th>Niepoprawna odpowiedz 1</th>
                        <th>Niepoprawna odpowiedz 2</th>
                        <th>Data utworzenia</th>
                        <th>Data modyfikacji</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="quizQuestion" items="${quizquestions}">
                        <tr role="row" class="odd">
                            <td class="sorting_1">${quizQuestion.id}</td>
                            <td>${quizQuestion.questionText}</td>
                            <td>${quizQuestion.category.name}</td>
                            <td>${quizQuestion.correctAnswer.answerText}</td>
                            <td>${quizQuestion.firstIncorrectAnswer.answerText}</td>
                            <td>${quizQuestion.secondIncorrectAnswer.answerText}</td>
                            <td>${quizQuestion.createdOn}</td>
                            <td>${quizQuestion.lastModifiedOn}</td>
                            <td>
                                <a href="/admin/addeditquizquestion?id=${quizQuestion.id}">Edytuj</a>
                                <a href="/admin/deletequizquestion?id=${quizQuestion.id}" onclick="return confirm('Czy skasować?')">Skasuj</a>
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