#SEATTLE PLACES

*Aclaración: se utilizó la versión 3 de la Api de Foursquare ya que la V2 en la actualidad quedó Obsoleta.
 La misma no pide Client_ID ni Client_Secret, sino que pide Api key.

*Incorporé un searchView dentro de la ToolBar, el mismo predice la búsqueda y mientras el usuario escribe 
 se van presentando los datos en el RecyclerView.

*Cuando se hace click sobre un item, se abre la segunda Screen donde podremos ver a detalle los datos del lugar
 su ubicación exacta en el google maps, la distancia, etc.

##Para el desarrollo de esta app se Utilizaron:

* MVVM
* Navigation Component (con animación en las transiciones)
* Retrofit2
* Google Maps
* FourSquare Api V3
* Clean Arquitecture
* Dagger Hilt
