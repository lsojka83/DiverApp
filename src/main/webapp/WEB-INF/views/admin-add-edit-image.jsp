<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD obrazów</h1>
    </div>

    <form name="fileUpload" method="POST" action="/admin/addimage" enctype="multipart/form-data">
    <label>Wybierz plik:</label> <br>
    <input type="file" name="multipartFile" class="btn btn-warning"/>
     <br>
     <%--
     <input type="file" accept="image/*" id="file-input" />
     <script>
       const fileInput = document.getElementById('file-input');

       fileInput.addEventListener('change', (e) =>
         doSomethingWithFiles(e.target.files),
       );
     </script>
     <div id="target">You can drag an image file here</div>
     <script>
       const target = document.getElementById('target');

       target.addEventListener('drop', (e) => {
         e.stopPropagation();
         e.preventDefault();

         doSomethingWithFiles(e.dataTransfer.files);
       });

       target.addEventListener('dragover', (e) => {
         e.stopPropagation();
         e.preventDefault();

         e.dataTransfer.dropEffect = 'copy';
       });
     </script>
--%>


     <br>
    <input type="text" name="name" id="name" value="">
    <label for="name">Nazwa</label>
    <p></p>

                <button class="btn btn-success" type="submit" name="confirm"
                        value="yes">Zapisz</button>
                <button class="btn btn-danger" type="submit" name="confirm"
                value="no" formnovalidate>Zrezygnuj</button>

     <%-- <input type="submit" name="submit" value="Wyœlij" class="btn btn-success"/>--%>
    </form>

</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<%@ include file="/WEB-INF/views/jspf/admin-footer.jspf" %>

