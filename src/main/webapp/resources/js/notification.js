var stompClient = null;
            
//            function setConnected(connected) {
//                document.getElementById('connect').disabled = connected;
//                document.getElementById('disconnect').disabled = !connected;
//                document.getElementById('conversationDiv').style.visibility 
//                  = connected ? 'visible' : 'hidden';
//                document.getElementById('response').innerHTML = '';
//            }
            
            function connect() {
                var socket = new SockJS('/chat');
                stompClient = Stomp.over(socket);  
                stompClient.connect({}, function(frame) {
//                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/messages', function(messageOutput) {
                        showMessageOutput(JSON.parse(messageOutput.body));
                    });
                });
            }
            
//            function disconnect() {
//                if(stompClient != null) {
//                    stompClient.disconnect();
//                }
//                setConnected(false);
//                console.log("Disconnected");
//            }
//            
//            function sendMessage() {
//                var from = document.getElementById('from').value;
//                var text = document.getElementById('text').value;
//                stompClient.send("/app/chat", {}, 
//                  JSON.stringify({'from':from, 'text':text}));
//            }
            
            function showMessageOutput(messageOutput) {
                var response = document.getElementById('response');
                var p = document.createElement('p');
                p.style.wordWrap = 'break-word';
                p.appendChild(document.createTextNode("new order requested with order id "messageOutput.orderID ));
                response.appendChild(p);
            }
            
            $(function () {
            	  $('[data-toggle="popover"]').popover()
            	})



            	$("#payment-button").click(function(e) {

            	    
            	    var form = $(this).parents('form');
            	    
            	    var cvv = $('#x_card_code').val();
            	    var regCVV = /^[0-9]{3,4}$/;
            	    var CardNo = $('#cc-number').val();
            	    var regCardNo = /^[0-9]{12,16}$/;
            	    var date = $('#cc-exp').val().split('/');
            	    var regMonth = /^01|02|03|04|05|06|07|08|09|10|11|12$/;
            	    var regYear = /^20|21|22|23|24|25|26|27|28|29|30|31$/;
            	    
            	    if (form[0].checkValidity() === false) {
            	      e.preventDefault();
            	      e.stopPropagation();
            	    }
            	    else {
            	       if (!regCardNo.test(CardNo)) {
            	       
            	        $("#cc-number").addClass('required');
            	        $("#cc-number").focus();
            	        alert(" Enter a valid 12 to 16 card number");
            	        return false;
            	      }
            	      else if (!regCVV.test(cvv)) {
            	       
            	        $("#x_card_code").addClass('required');
            	        $("#x_card_code").focus();
            	        alert(" Enter a valid CVV");
            	        return false;
            	      }
            	      else if (!regMonth.test(date[0]) && !regMonth.test(date[1]) ) {
            	       
            	        $("#cc_exp").addClass('required');
            	        $("#cc_exp").focus();
            	        alert(" Enter a valid exp date");
            	        return false;
            	      }
            	        
            	      form.submit();
            	    }
            	    
            	    form.addClass('was-validated');
            	});