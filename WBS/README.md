Design Goals

e) Appropriate use of the Strategy Pattern or Template Method Pattern.
  The strategy pattern was implemented to contain the sub-menu options that determine the reconciliation approach when the estimates obtained by the estimators are not equal. The estimate effort menu option is the context for the sub-menu strategy interface. The strategy interface consists of four concrete strategies for each of the reconciliation approaches: determine the highest effort estimate; determine the median effort estimate; request a single revised effort estimate; or, should the user enter an invalid reconciliation approach, simply set the effort estimate as unknown.
  
  As an alternative, the strategy pattern was initially implemented to contain the main menu options that consist of: effort estimate, configure, and quit. That strategy pattern was duplicated to implement the sub-menu options. However, as the sub-menu options seemed to better fit the use case of the strategy pattern, the main menu options were converted to methods.

f) Appropriate use of the Decorator Pattern or Composite Pattern.
  The composite pattern was implemented to contain the tasks within the work breakdown structure (WBS) due to the inherent hierarchical nature. There are four task variants that each share the same underlying components: a task consists of an ID, a description, and an optional effort estimate; a task as a sub-task has those attributes alongside a super-task; a super-task consists of an ID, a description, and optional sub-task(s); and, a super-task as a sub-task has those attributes alongside a super-task. The WBS is the context for the task component interface. The component interface consists of a super-task as the composite class and a sub-task as the leaf class. The WBS itself is very similar to the super-task with almost identical methods.
  
  As an alternative, the tasks within the WBS may be implemented using the decorator pattern. While not as intuitive as the composite pattern, the decorator could start with a base task, that would be wrapped with decorators depending on the four task variants previously mentioned.

b) Appropriate use of a map.
  A map was used within the WBS to contain all tasks, including super-tasks and sub-tasks. Additionally, a map was used within each super-task to hold all associated sub-tasks.

g) Clear and correct class diagram of your whole application.
  I did not include accessors and mutators methods unless necessary. For example, I only included accessors and mutators methods for the default class as that class is used as an intermediate object to store the number of estimators and the reconciliation approach.

a) General code quality, as assessed by linting tool(s).
  In both the context WBS and the composite class SuperTask, the PMD warning “AvoidReassigningLoopVariables” was suppressed within the find method as recursion is required to find a task that may be a sub-task many hierarchical layers deep.
