<%--
  Created by IntelliJ IDEA.
  User: o2122061
  Date: 15/06/17
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Admin</title>
    <sj:head/>
    <sb:head/>
</head>
<body>
    <script>
        $(document).ready(function()
        {
            $('.selectUser').submit(function(e)
            {
                e.preventDefault();
                var data = $(this).serialize();

                $.ajax({
                    url: "selectUser.action?" + data,
                    type: "POST",
                    contentType: "application/json: charset=utf-8",
                    dataType: "json",
                    success: function(result)
                    {
                        var updateUserForm =
                                '<form id="updateUser" class="updateUser" name="updateUser" ' +
                                'action="/updateUser.action" method="post">' +
                                    '<fieldset>' +
                                        '<input id="password" name="password" placeholder="Nouveau mot de passe"> </input> </br></br>' +
                                        'Cochez les rôles que vous souhaitez supprimer: <br/><br/>';
                        var cpt = 0;
                        for(var role in result.roles)
                        {
                            if(result.roles[cpt] == 'ADMIN')
                            {
                                updateUserForm +=
                                        '<input id="deleteAdminRole" name="deleteAdminRole" type="checkbox" value="true"> Admin </input> </br></br>';
                            } else if(result.roles[cpt] == 'VENDEUR')
                            {
                                updateUserForm +=
                                        '<input id="deleteSellerRole" name="deleteSellerRole" type="checkbox" value="true"> Vendeur </input> </br></br>';
                            } else if(result.roles[cpt] == 'RESPONSABLESTOCK')
                            {
                                updateUserForm +=
                                        '<input id="deleteStockManagerRole" name="deleteStockManagerRole" type="checkbox" value="true"> Responsable des stocks </input> </br></br>';
                            }
                            cpt++;
                        }
                        updateUserForm +=
                                        'Nouveau(x) rôle(s) si nécessaire: <br/><br/> ' +
                                        '<select multiple id="rolesToAdd" name="rolesToAdd" style="width: 200px">';

                        if(result.roles.indexOf('ADMIN') == -1)
                        {
                            updateUserForm +=
                                            '<option value="ADMIN">Admin</option>';
                        }
                        if(result.roles.indexOf('RESPONSABLESTOCK') == -1)
                        {
                            updateUserForm +=
                                            '<option value="RESPONSABLESTOCK">Responsable des stocks</option>';
                        }
                        if(result.roles.indexOf('VENDEUR') == -1)
                        {
                            updateUserForm +=
                                            '<option value="VENDEUR">Vendeur</option>';
                        }
                        updateUserForm +=
                                        '</select><br/><br/>' +
                                        '<input id="updateUserSubmit" class="btn btn-primary center-block " value="Valider" type="submit"> </input>' +
                                    '</fieldset>' +
                                '</form>';
                        $('#updateUserForm').html(updateUserForm);
                    },
                    error: function()
                    {
                        alert('Erreur!');
                    }
                });
            });
        });
    </script>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <center><h1>Menu Admin</h1></center>
            </div>
            <div class="col-md-offset-2 col-md-2">
                <br/>
                <br/>
                <s:form action="menu" theme="bootstrap">
                    <s:hidden name="password" value="%{#session.password}"/>
                    <s:submit cssClass="btn btn-primary" value="Retour"/>
                </s:form>
            </div>
        </div>
        <br/>
        <br/>
        <div class="row">
            <div class="col-md-offset-1 col-md-2">
                <h3>Ajout d'un nouvel utilisateur</h3>
                <br/>
                <s:form action="addUser" theme="bootstrap">
                    <s:textfield
                            id="login"
                            name="login"
                            placeholder="Login de l'utilisateur"/>
                    <s:textfield
                            id="password"
                            name="password"
                            placeholder="Son mot de passe"/>
                    <s:textfield
                            id="passwordConfirmation"
                            name="passwordConfirmation"
                            placeholder="Confirmation mot de passe"/>
                    <s:submit id="btn-submit" cssClass="btn btn-primary pull-right" value="Ajouter"/>
                </s:form>
                <br/>
                <br/>
            </div>
            <div class="col-md-offset-1 col-md-2">
                <h3>Suppression d'un utilisateur</h3>
                <br/>
                <s:form action="deleteUser" theme="bootstrap">
                    <select id="userSelected" name="userSelected">
                        <s:iterator var="user" value="%{session.users}">
                            <option value="<s:property value="%{#user.getIdentifiant()}"/>"><s:property value="%{#user.getNom()}"/></option>
                        </s:iterator>
                    </select>
                    <br/>
                    <br/>
                    <s:submit cssClass="btn btn-primary" value="Supprimer"/>
                </s:form>
            </div>
            <div class="col-md-offset-1 col-md-3">
                <h3>Mise à jour d'un utilisateur</h3>
                <br/>
                <s:form action="selectUser" theme="bootstrap" cssClass="selectUser">
                    <select id="userSelected" name="userSelected">
                        <s:iterator var="user" value="%{session.users}">
                            <option value="<s:property value="%{#user.getIdentifiant()}"/>"><s:property value="%{#user.getNom()}"/></option>
                        </s:iterator>
                    </select>
                    <br/>
                    <br/>
                    <s:submit cssClass="btn btn-primary" value="Sélectionner"/>
                </s:form>
                <br/>
                <br/>
                <div id="updateUserForm"></div>
            </div>
        </div>
    </div>
</body>
</html>
