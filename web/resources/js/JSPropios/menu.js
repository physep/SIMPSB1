  $('.dropdown-menu').on('click', function (e) {
    e.stopPropagation();
  });

  function Agenda()
      {
  
            if ($(".nav-link").data("valor")=="1")
              { 
                $('.dropdown-menu').removeClass('show');
                $(".nav-link").data("valor", "0");
              }
              else
              {
                $('.dropdown-menu').addClass('show');
                $(".nav-link").data("valor", "1");
              }
          
      }
  
  