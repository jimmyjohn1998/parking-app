<%@page import="com.parkingapp.bean.UserBean"%>
<%@page import="com.parkingapp.bean.AreaAdminRegisterBean"%>
<%@page import="java.util.ArrayList"%>
<%@include file="admin_header.jsp" %>
<style>
    .banner-dott{
        height: 200px;
    }
</style>
<div class="container" style="min-height: 400px;">
    <br/>
    <h2>Users List</h2> <br/>
    <input class="form-control" id="myInput" type="text" placeholder="Search.." style="background-color: dark"><br/>
    <table class="table">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Username</th>
                <th>Email id</th>
                <th>Phone number</th>
                <th>Balance</th>               
                <!--                <th>Options</th>-->
            </tr>
        </thead>
        <tbody id="myTable">
            <%
                Object object = request.getAttribute("temp");
                if (object != null && object instanceof ArrayList) {
                    ArrayList<UserBean> list = (ArrayList<UserBean>) object;
                    for (int i = 0; i < list.size(); i++) {
                        UserBean bean = list.get(i);
            %>
            <tr>
                <td><%= i + 1%></td>
                <td><%= bean.getName()%></td>
                <td><%= bean.getUsername()%></td>
                <td><%= bean.getEmail()%></td>
                <td><%= bean.getPhone()%></td>
                <td><%= bean.getBalance()%></td>

                <!--                <td><a href=""> Approve </a></td>-->
            </tr>

            <%              }
                }%>
        </tbody>
    </table>
</div>
<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
<%@include file="admin_footer.jsp" %>