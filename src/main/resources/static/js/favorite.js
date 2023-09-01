angular.module('favoriteApp', [])
    .controller('FavoritesController', function($scope, $http) {

        $scope.categories = [];
        $scope.realCategories = [];
        $scope.favorites = [];

        $scope.filter = {
            category: 0
        };

        $scope.mode = 'view';

        $scope.favorite = {};
        $scope.category = {};
        $scope.favoritesToDelete = [];
        $scope.idToDelete = [];

        $scope.setMode = function(text) {
            if (text === 'creation') {
                $scope.realCategories = $scope.categories.filter(function(c) { return c.id !== 0 });
                var idx = $scope.realCategories.map(function(c) { return c.id }).indexOf($scope.filter.category );
                if (idx < 0) idx = 0;

                $scope.favorite = {
                    link: '',
                    category: $scope.realCategories[idx].id
                }
            }
            $scope.mode = text;
        }

        $scope.update = function(f){
            $scope.favorite = {id: f.id, link: f.link, name: f.name, category: f.category.id};
            $scope.realCategories = $scope.categories.filter(function(c) { return c.id !== 0 });
            var idx = $scope.realCategories.map(function(c) { return c.id }).indexOf($scope.filter.category );
            console.log($scope.favorite)
            $scope.setMode('edition');


        }

        $scope.cancel = function() {
            $scope.setMode('view');
        }

        $scope.deleteMultiple = function(){
        $scope.favoritesToDelete = $scope.favorites.filter((f) => f.selected === true);
        $scope.idToDelete = $scope.favoritesToDelete.map((f) => f.id)
        if($scope.idToDelete.length == 0){
            alert("No item selected, please select at least one item");
        }else{
                $http.delete('api/favorites/' + $scope.idToDelete.join(',')).then(function(){
                    $scope.refresh();
                })
                    console.log($scope.idToDelete)
            }
        }

        $scope.validate = function() {
        if ($scope.mode == 'creation' || $scope.mode == 'edition'){
                    $http.post('api/category/' + $scope.favorite.category + '/favorites' , {id: $scope.favorite.id, name:$scope.favorite.name, link: $scope.favorite.link }).then(
                        function() {
                            $scope.refresh();
                            $scope.setMode('view');
                        }, function(error) {
                            alert(error.data.message);
                        }
                    )
                    console.log("test")
        }
        if ($scope.mode == 'creation category'){
                    $http.post('api/categories', {id: null, name:$scope.category.name}).then(

                        function(){
                        Swal.fire({
                          position: 'center',
                          icon: 'success',
                          title: 'Your category has been created',
                          showConfirmButton: false,
                          timer: 1500
                        })
                           $scope.refresh();
                            $scope.setMode('view');
                        }, function(error) {
                            alert(error.data.message);
                        }
                    )
        }
        }
        $scope.delete = function(id) {
        Swal.fire({
          title: 'Are you sure?',
          text: "You won't be able to revert this!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
          if (result.isConfirmed) {
                      $http.delete('api/favorites/' +id).then(
                          function(){
                              $scope.refresh();
                          }
                      )
          Swal.fire(
              'Deleted!',
              'Your file has been deleted.',
              'success'
            )
          }
        })

        }

        $scope.refresh = function() {
            $http.get('api/categories').then(
                function(response) {
                    $scope.categories = [{id: 0, name: "Everything", references: 0}];
                    response.data.forEach(d => {
                        $scope.categories.push(d);
                    })

                    $http.get('api/favorites').then(
                        function(response) {
                            $scope.favorites = response.data.filter(f => $scope.filter.category === 0 || f.category.id === $scope.filter.category);

                        }
                    )


                }
            )
        }


        $scope.refresh();
    });