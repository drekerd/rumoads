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
  <div class="centered">

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
        </tr>
      </#list>
      </tbody>
    </table>
  </body>
</html>