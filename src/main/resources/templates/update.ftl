
<h2> Current Data</h2>
<td>Course Name: <strong>${add.addName}</strong></td></br>
<td>Course Description: <strong>${add.addDescription}</strong></td></br>
<td>Course Price: <strong>${add.addPrice}</strong></td></br>

</br>

<form method="POST" action='/admin/edit' name="updateAdd" id="updateAdd" target="_self">

     Add ID : <input type="text" readonly="readonly" name="addId" value="${add.addId}"/>
      
            Add Title : <input type="text" name="addName" required"/>
      
            Add Description : <input type="text" name="addDescription" required/>
      
            Price : <input type="number" name="addPrice" required/>
    <input type="submit" value="Submit"/>
    <button onclick="location.href='http://localhost:8080/admin'" type="button">
        Cancel
    </button>
</form>
