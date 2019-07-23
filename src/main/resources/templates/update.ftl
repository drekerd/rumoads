<form method="POST" action='/admin/edit' name="updateAdd" id="updateAdd" target="_self">

            Add ID : <input type="text" readonly="readonly" name="addId" value="${add.addId}"/>
      
            Add Title : <input type="text" name="addName" required value="${add.addName}"/>
      
            Add Description : <input type="text" name="addDescription" required value="${add.addDescription}"/>
      
            Price : <input type="number" name="addPrice" required value="${add.addPrice}"/>
    <input type="submit" value="Submit"/>
    <button onclick="location.href='http://localhost:8080/admin'" type="button">
        Cancel
    </button>
</form>