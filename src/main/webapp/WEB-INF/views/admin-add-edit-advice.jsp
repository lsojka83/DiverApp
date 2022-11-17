<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD porad</h1>

    </div>

    <form:form action="/admin/addeditadvice" method="post" modelAttribute="advice">
        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Dodaj poradê</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td>Nazwa</td>
                            <td>
                                <form:input path="name" type="text" name="name" placeholder="Nazwa"  />
                                <form:errors path="name"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Treœæ</td>
                            <td>
                                <form:textarea path="adviceText" type="text" name="adviceText" placeholder="Treœæ" rows="5" cols="20"/>
                                <form:errors path="adviceText"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Kategoria</td>
                            <td>
                                <form:select path="category">
                                <form:option value='1' label="-Wybierz kategorie-"/>
                                <form:options items="${categories}" itemLabel="name" itemValue="id"/>
                                </form:select>
                                <form:errors path="category"/>
                            </td>
                        </tr>
                        <tr>
                         <td>Obrazek</td>
                            <td>
                                <form:select path="image">
                                 <form:option value='0' label="-Wybierz obraz-"/>
                                 <form:options items="${images}" itemLabel="name" itemValue="id"/>
                                  </form:select>
                                <form:errors path="image"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Film</td>
                            <td>
                                <form:select path="movie" items="${movies}" itemLabel="name" itemValue="id" label="Wybierz film"/>
                                <form:errors path="movie"/>
                            </td>
                        </tr>

                        <tr>
                            <td>Pytania</td>
                            <td>
                                <form:select path="quizQuestions" items="${quizQuestions}" itemLabel="questionText" itemValue="id" multiple="true"/>
                                <form:errors path="quizQuestions"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Ocena</td>
                            <td>
                                <form:input path="rating"  name="rating" placeholder="Ocena"  />
                                <form:errors path="rating"/>
                            </td>
                        </tr>
                           <tr>
                            <td>Liczba ocen</td>
                            <td>
                                <form:input path="ratingsCount" name="ratingsCount" placeholder="Liczba ocen"  />
                                <form:errors path="ratingsCount"/>
                            </td>
                        </tr>
                        </tbody>

                    </table>

                </div>
            </div>
        </div>


        <form:hidden path="id"/>
        <form:hidden path="createdOn"/>

        <div class="text-center">
            <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="submit" name="confirm"
                    value="yes">Zapisz</button>
            <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="submit" name="confirm"
            value="no" formnovalidate> Zrezygnuj </button>
        </div>
    </form:form>

</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<%@ include file="/WEB-INF/views/jspf/admin-footer.jspf" %>
