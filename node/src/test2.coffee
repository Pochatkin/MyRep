reverse = (str) -> 
            for i in [0..str.length % 2]
                temp = str[i] 
                str[i] = str[str.length - i]
                str[str.length - i] = temp

        $("#button").click -> 
            a = $("#a").val() 
            $("#c").html a;