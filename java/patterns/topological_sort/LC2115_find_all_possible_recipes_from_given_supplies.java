class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Set<String>> ingredientToRecipes = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (int i=0;i<recipes.length;++i){
            for (String ing : ingredients.get(i)){
                ingredientToRecipes.computeIfAbsent(ing, s -> new HashSet<>()).add(recipes[i]);
            }
            inDegree.put(recipes[i], ingredients.get(i).size());
        }

        // Topological Sort
        List<String> ans = new ArrayList<>();
        Queue<String> available = Stream.of(supplies).collect(Collectors.toCollection(LinkedList::new));
        while (!available.isEmpty()){
            String ing = available.poll();
            if (ingredientToRecipes.containsKey(ing)){
                for (String rcp : ingredientToRecipes.remove(ing)){
                    if (inDegree.merge(rcp, -1, Integer::sum) == 0){
                        available.offer(rcp);
                        ans.add(rcp);
                    }
                }
            }
        }
        return ans;
    }
}