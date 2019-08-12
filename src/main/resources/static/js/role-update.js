$("#role-form").submit(function(event){
            event.preventDefault();
            var form = $(this);
            var name = form.find('input[name="name"]').val();

            var fullpathname = window.location.href;

            var url = form.attr('action');
            var jsonString = JSON.stringify({
                            name: name
            });

           $.ajax({
                type : 'POST',
                url : url,
                contentType: 'application/json',
                data : jsonString,
                success : function(data, status, xhr){
                   window.location='/roles'
                },
                error: function(xhr, status, error){
                  alert(error);
                }
            });
});