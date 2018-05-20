
  function modify(client_id) {
    $.ajax({
      url:"/client/findById?id=chlient_id",
      type:"get",
      data:{
        "id":client_id
    },
    dataType:"json",
      success:function (data) {  
      var json = data.client;
      var client_name = json.client_name;
      var client_person = json.client_person;
      var client_address = json.client_address;
      var client_phone = json.client_phone;
      console.log(client_name);
      function() {
    	  window.location.href ="/client/modifyUI";
    	  
    	  console.log('34');
	}
      }
    
    })
  }
