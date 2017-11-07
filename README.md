# fit-based-clustering-using-j48-decision-trees
The fit-based clustering approach clusters participants without using any additional information. It instead uses  the fit of the tree models to bootstrap the process of sorting participants into clusters. Like many bootstrapping  methods, ours uses random starts and iterative improvements to find the optimal solution.  

Random starts: We randomly divide particpants over N separate groups, and learn a tree for each group. This is repeated until a non-trivial starting solution (i.e., with distinctly different trees per cluster) is found. 

Iterative improvements: Once each of the N groups  has a unique decision tree, we evaluate for each participant which of the trees best represents their 14 decisions.  If this is the tree of a different group, we switch the participant to this group. Once all participants are evaluated and put in the group of their best-fitting tree, the  tree in each group is re-learned with the data of the new group members. This then prompts another round of evaluations, and this process continues until no further switches are performed. Since this process is influenced by random chance, it is repeated in its entirety to find the optimal solution. Cross-validation is performed in the final step to prevent over-fitting.

Flowchart:
![flowchartfit](https://user-images.githubusercontent.com/24614382/32476134-809c183a-c343-11e7-8dd4-db4ff9c2d80f.png)

Results:
![fit based 3 new](https://user-images.githubusercontent.com/24614382/32476204-d26207ec-c343-11e7-9971-d09551da553a.jpg)
