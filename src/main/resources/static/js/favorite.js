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

            if (text === 'edit'){
                console.log('its ok')
            }
            $scope.mode = text;
        }

        $scope.update = function(f){
            $scope.favorite = {id: f.id, link: f.link, name: f.name, category: f.category.id};
            $scope.realCategories = $scope.categories.filter(function(c) { return c.id !== 0 });
            var idx = $scope.realCategories.map(function(c) { return c.id }).indexOf($scope.filter.category );
            console.log($scope.favorite)
            $scope.setMode('edition');


//            console.log(favorite.id, favorite.name, favorite.link, favorite.updatedDate, favorite.category)
        }

        $scope.cancel = function() {
            $scope.setMode('view');
        }


        $scope.validate = function() {
            $http.post('api/category/' + $scope.favorite.category + '/favorites' , {id: $scope.favorite.id, name:$scope.favorite.name, link: $scope.favorite.link }).then(
                function() {
                    $scope.refresh();
                    $scope.setMode('view');
                }, function(error) {
                    alert(error.data.message);
                }
            )
        }

        $scope.delete = function(id) {
            $http.delete('api/favorites/' +id).then(
                function(){
                    $scope.refresh();

                    console.log($scope.test)
                }
            )
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