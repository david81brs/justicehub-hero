$("#form-update").submit(function(event){
            event.preventDefault();
            var form = $(this);
            var idVal = form.find('input[name="id"]').val();

            var eventTitle = form.find('input[name="eventTitle"]').val();
            var eventLocation = form.find('input[name="eventLocation"]').val();
            var eventStartDate = form.find('input[name="eventStartDate"]').val();
            var eventEndDate = form.find('input[name="eventEndDate"]').val();
            var peopleAttended = form.find('input[name="peopleAttended"]').val();
            var informer = form.find('input[name="informer"]').val();
            var contacPerson = form.find('input[name="contacPerson"]').val();

            var fullpathname = window.location.href;

            var url = form.attr('action');
            var jsonString = JSON.stringify({
                id: idVal,
                eventTitle: eventTitle,
                eventLocation:eventLocation,
                eventStartDate: eventStartDate,
                eventEndDate: eventEndDate,
                peopleAttended: peopleAttended,
                informer: informer,
                contacPerson: contacPerson
                });

            console.log(jsonString);
            $.ajax({
                type : 'PUT',
                url : url,
                contentType: 'application/json',
                data : jsonString,
                success : function(data, status, xhr){
//                   $("#result").html(data+
//                   " link: <a href='"+fullpathname+"'>"+fullpathname+"</a>");
                   window.location='/index'
                },
                error: function(xhr, status, error){
                  alert(error);
                }
            });

        });