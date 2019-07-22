<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Rumos Adds</title>
</head>
<body>

    
<form method="POST" action='/admin' name="newAdd" id="newAddForm" target="_self">

           <!-- Add ID : <input type="text" readonly="readonly" name="addId"/> -->
      
            Add Title : <input type="text" name="addName" required/>
      
            Add Description : <input type="text" name="addDescription" required/>
      
            Price : <input type="number" name="addPrice" required/>
     
    <input type="submit" value="Submit"/>
</form>

</br>

<form method="post" action="/admin/sync" target="_self">
    <input type="submit" value="Sync Courses">
</form>
<#if lastSync??>
    <p>Last synced at ${lastSync}</p>

<!--    <div>Never synced. If automatic data is not synced, please try it manually</div>-->
</#if>


<div class="centered">

    <#if addsFromBE?has_content>
        <table class="table table-striped">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Course</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
            </tr>
            </thead>
            <tbody>
            <#list addsFromBE as item>
                <tr>
                    <th scope="row">${item.addId?string.computer}</th>
                    <td>${item.addName}</td>
                    <td>${item.addDescription}</a></td>
                    <td>${item.addPrice?string.computer}</td>
                    <td>
                        <form method="post" action="/admin/delete?id=${item.addId}" target="_self">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

    <#else>

        <!--table if list is null-->
        <h1>No Adds To Display</h1>
    </#if>
</body>
</html>