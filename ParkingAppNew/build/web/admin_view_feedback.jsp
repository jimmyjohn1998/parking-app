<%@page import="com.parkingapp.bean.FeedbackBean"%>
<%@page import="com.parkingapp.bean.BookingBean"%>
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
    <h2>Booking List</h2> <br/>
    <input class="form-control" id="myInput" type="text" placeholder="Search.." style="background-color: dark"><br/>
    <table class="table">
        <thead>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Area Admin Name</th>
                <th>Feedback</th>
            </tr>
        </thead>
        <tbody id="myTable">
            <%
                Object object = request.getAttribute("temp");
                if (object != null && object instanceof ArrayList) {
                    ArrayList<FeedbackBean> list = (ArrayList<FeedbackBean>) object;
                    for (int i = 0; i < list.size(); i++) {
                        FeedbackBean bean = list.get(i);
            %>
            <tr>
                <td><%= i + 1%></td>
                <td><%= bean.getUsername()%></td>
                <td><%= bean.getAreaadminname()%></td>
                <td><%= bean.getFeedback()%></td>                
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