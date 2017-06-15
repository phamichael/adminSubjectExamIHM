<%--
  Created by IntelliJ IDEA.
  User: o2122061
  Date: 15/06/17
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Menu</title>
    <sj:head/>
    <sb:head/>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <center><h1>Accueil</h1></center>
            </div>
            <div class="col-md-offset-2 col-md-2">
                <br/>
                <br/>
                <s:form action="disconnection" theme="bootstrap">
                    <s:submit cssClass="btn btn-primary" value="Déconnexion"/>
                </s:form>
            </div>
        </div>
        <br/>
        <br/>
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <s:if test="%{#session.isSeller}">
                    <s:form action="seller" theme="bootstrap">
                        <center><s:submit cssClass="btn btn-primary" value="Vendeur" style="width: 200px"/></center>
                    </s:form>
                </s:if>
            </div>
        </div>
        <br/>
        <br/>
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <s:if test="%{#session.isAdmin}">
                    <s:form action="admin" theme="bootstrap">
                        <center><s:submit cssClass="btn btn-primary" value="Administrateur" style="width: 200px"/></center>
                    </s:form>
                </s:if>
            </div>
        </div>
        <br/>
        <br/>
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <s:if test="%{#session.isStockManager}">
                    <s:form action="stockManager" theme="bootstrap">
                        <center><s:submit cssClass="btn btn-primary" value="Responsable des stocks" style="width: 200px"/></center>
                    </s:form>
                </s:if>
            </div>
        </div>
    </div>
</body>
</html>
