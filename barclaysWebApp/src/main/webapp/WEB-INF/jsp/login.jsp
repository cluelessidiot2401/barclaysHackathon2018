<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/resources/css/login.css' /> "/>
</head>
<body>

<h2>Welcome to Barclays Bank</h2>

<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Sign Up</button>
<button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Log in</button>

<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form:form class="modal-content" modelAttribute="login"
      id="login-form" method="post" action="registerProcess">
      <form:errors path="*" cssClass="errorblock" element="div" />
      <div class="container">
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="name"><b>Name</b></label>
        <form:input type="text" path="userInfo.name" name="name" id="name"
          required="required" /><br/>
        <label for="email"><b>Email</b></label>
        <form:input type="text" placeholder="Enter Email"
          path="userInfo.emailId" name="email" required="true" /><br/>
        <label for="phonenum"><b>Phone Number (format:
            xxxx-xxx-xxxx):</b></label><br />
        <form:input id="phonenum" path="userInfo.phoneNo"
          placeholder="Enter Mobile no" type="tel"
          pattern="^\d{4}-\d{3}-\d{4}$" name="phoneno" required="true" /><br/>
        <label for="panno"><b>Pan card Number (format:
            xxxxx9999x):</b></label><br />
        <form:input id="panno" path="userInfo.panNo" type="text"
          placeholder="Enter Pan-Card no"
          pattern="[A-za-z]{5}\d{4}[A-Za-z]{1}" name="panno" required="true" /><br/>

        <label for="gender"> <b>Choose Gender</b>
        </label> <br>
        <form:radiobutton path="userInfo.gender" name="gender" value="male"
          required="required" />
        Male<br>
        <form:radiobutton path="userInfo.gender" name="gender"
          value="female" required="required" />
        Female<br>
        <form:radiobutton path="userInfo.gender" name="gender" value="other"
          required="required" />
        Other<br> <br> <label for="dob"> <b>Choose
            Date of Birth</b>
        </label> <br />
        <form:input type="date" path="userInfo.dob" name="dob"
          required="true" />
        <br /> <br /> <label for="psw"><b>Password</b></label><br />
        <form:input type="password" path="loginDetails.uPassword"
          placeholder="Enter Password" name="psw" required="true" /><br/>
        <label for="psw-repeat"><b>Repeat Password</b></label><br /> <input
          type="password" placeholder="Repeat Password" name="psw-repeat"
          required="required"> <label> <input type="checkbox"
          checked="checked" name="remember" style="margin-bottom: 15px">
          Remember me
        </label>

        <p>
          By creating an account you agree to our <a href="#"
            style="color: dodgerblue">Terms and Privacy</a>.
        </p>

        <div class="clearfix">
          <button type="button"
            onclick="document.getElementById('id01').style.display='none'"
            class="cancelbtn">Cancel</button>
          <button type="submit" class="signupbtn">Sign Up</button>
        </div>
      </div>
    </form:form>
</div>

<div id="id02" class="modal">
  <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form:form modelAttribute="login" class="modal-content"
      action="loginProcess" required="true">
      <div class="container">
        <h1>Log in</h1>
        <p>Please fill in this form to log in to your account.</p>
        <hr>

        <label for="email"><b>Email</b></label>
        <form:input type="text" placeholder="Enter Email"
          path="userInfo.emailId" name="email" required="required" /><br/>
        <label for="psw"><b>Password</b></label>
        <form:input type="password" path="loginDetails.uPassword" name="psw" /><br/>


        <div class="clearfix">
          <button type="button"
            onclick="document.getElementById('id02').style.display='none'"
            class="cancelbtn">Cancel</button>
          <button type="submit" class="signupbtn">Log In</button>
        </div>
      </div>
    </form:form>
</div>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>

</body>
</html>

