<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />	
	<title><fmt:message key="title" /></title>	
	<style>
    	.error { color: red; }
    	.contenedorHead{background: gray;padding:8px;}
		.contenedorHead h1{	color: white;}
    	.button{margin-bottom: 70px;	border-radius: 5px;	background: navy;	color: white;	border: none;	padding: 7px;	}
 	</style> 	 	
</head>
<body>	
<div class="contenedorHead">
	<h1 style="color: white;">
		<fmt:message key="heading" />
	</h1>
</div>
<div class="contenedorBody">
	<h3>Ingresar información</h3>
	<form:form method="post" commandName="loadingInformation">
	  <table>
	    <tr>
    		<td align="left" width="20%">Cédula Participante :</td>
	        <td width="20%">
	          <form:input path="cedula"/>
	        </td>
	        <td width="60%">
	          <form:errors path="cedula" cssClass="error"/>
	        </td>
	    </tr>	    
   	    <tr>
	   		<td align="left" width="20%">Seleccionar Archivo :</td>
	        <td width="20%" >
	          <form:input type="file" path="pathFile" />
	        </td>		                	        
	        <td width="60%">
	          <form:errors path="pathFile" cssClass="error"/>
	        </td>
	    </tr>
	  </table>
	  <br>
	  <input type="submit" value="Procesar" class="button" >
	</form:form>
</div>		
</body>
</html>