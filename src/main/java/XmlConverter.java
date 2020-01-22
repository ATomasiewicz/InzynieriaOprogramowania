import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class XmlConverter {

    public XmlConverter(Project project) throws IOException {
        makeLabels(project);
    }

    public void makeLabels(Project project) throws IOException {

        List<IdClass> list=new LinkedList<>();
        List<IdClass> list2 = new LinkedList<>();

        PrintWriter printWriter = new PrintWriter("graf_plikow.xml");
        PrintWriter printWriter1 = new PrintWriter("graf_metod.xml");

        printWriter.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        printWriter.print("<Project Author=\"HP\" CommentTableSortAscending=\"false\" CommentTableSortColumn=\"Date Time\" DocumentationType=\"html\" ExportedFromDifferentName=\"false\" ExporterVersion=\"12.2\" Name=\"untitled\" TextualAnalysisHighlightOptionCaseSensitive=\"false\" UmlVersion=\"2.x\" Xml_structure=\"simple\">\n");
        printWriter.print("\t<Models>   \n");

        printWriter1.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        printWriter1.print("<Project Author=\"HP\" CommentTableSortAscending=\"false\" CommentTableSortColumn=\"Date Time\" DocumentationType=\"html\" ExportedFromDifferentName=\"false\" ExporterVersion=\"12.2\" Name=\"untitled\" TextualAnalysisHighlightOptionCaseSensitive=\"false\" UmlVersion=\"2.x\" Xml_structure=\"simple\">\n");
        printWriter1.print("\t<Models>   \n");

        PrintWriter printWriter2 = new PrintWriter("graf_metod_w_plikach.xml");

        printWriter2.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        printWriter2.print("<Project Author=\"HP\" CommentTableSortAscending=\"false\" CommentTableSortColumn=\"Date Time\" DocumentationType=\"html\" ExportedFromDifferentName=\"false\" ExporterVersion=\"12.2\" Name=\"untitled\" TextualAnalysisHighlightOptionCaseSensitive=\"false\" UmlVersion=\"2.x\" Xml_structure=\"simple\">\n");
        printWriter2.print("\t<Models>   \n");


        int fileNumber=0;
        for (File file : project.getFiles()) {
            String fileId= PasswordGenerator.generatePassword(12);
            list.add(new IdClass(file.getName(),fileId));
            printWriter.print("\t\t<Class Abstract=\"false\" Active=\"false\" BacklogActivityId=\"0\" BusinessKeyMutable=\"true\" BusinessModel=\"false\" ConnectToCodeModel=\"1\" Documentation_plain=\"\" Id=\"" + list.get(fileNumber).id+ "\" Leaf=\"false\" Name=\"" + list.get(fileNumber).fileName + "\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T17:34:30.747\" PmLastModified=\"2019-12-17T17:35:46.486\" QualityReason_IsNull=\"true\" QualityScore=\"-1\" Root=\"false\" UserIDLastNumericValue=\"0\" UserID_IsNull=\"true\" Visibility=\"public\">\n");
            printWriter.print("\t\t</Class>\n");

            //HIST 3
            printWriter2.print("\t\t<Class Abstract=\"false\" Active=\"false\" BacklogActivityId=\"0\" BusinessKeyMutable=\"true\" BusinessModel=\"false\" ConnectToCodeModel=\"1\" Documentation_plain=\"\" Id=\"" + list.get(fileNumber).id+ "\" Leaf=\"false\" Name=\"" + list.get(fileNumber).fileName + "\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T17:34:30.747\" PmLastModified=\"2019-12-17T17:35:46.486\" QualityReason_IsNull=\"true\" QualityScore=\"-1\" Root=\"false\" UserIDLastNumericValue=\"0\" UserID_IsNull=\"true\" Visibility=\"public\">\n");
            printWriter2.print("\t\t</Class>\n");

            fileNumber++;
        }


        int methodNumber=0;
        for(String method: project.findMethods())
        {
            String methodId= PasswordGenerator.generatePassword(12);
            list2.add(new IdClass(method,methodId));

            printWriter1.print("\t\t<Class Abstract=\"false\" Active=\"false\" BacklogActivityId=\"0\" BusinessKeyMutable=\"true\" BusinessModel=\"false\" ConnectToCodeModel=\"1\" Documentation_plain=\"\" Id=\"" + list2.get(methodNumber).id+ "\" Leaf=\"false\" Name=\"" + list2.get(methodNumber).fileName + "\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T17:34:30.747\" PmLastModified=\"2019-12-17T17:35:46.486\" QualityReason_IsNull=\"true\" QualityScore=\"-1\" Root=\"false\" UserIDLastNumericValue=\"0\" UserID_IsNull=\"true\" Visibility=\"public\">\n");
            printWriter1.print("\t\t</Class>\n");

            //HIST 3
            printWriter2.print("\t\t<Class Abstract=\"false\" Active=\"false\" BacklogActivityId=\"0\" BusinessKeyMutable=\"true\" BusinessModel=\"false\" ConnectToCodeModel=\"1\" Documentation_plain=\"\" Id=\"" + list2.get(methodNumber).id+ "\" Leaf=\"false\" Name=\"" + list2.get(methodNumber).fileName + "\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T17:34:30.747\" PmLastModified=\"2019-12-17T17:35:46.486\" QualityReason_IsNull=\"true\" QualityScore=\"-1\" Root=\"false\" UserIDLastNumericValue=\"0\" UserID_IsNull=\"true\" Visibility=\"public\">\n");
            printWriter2.print("\t\t</Class>\n");

            methodNumber++;
        }


        int c=0;
        Iterator iterator = project.getDependencies().iterator();
        while(iterator.hasNext()){
            FileDependency dependency = (FileDependency) iterator.next();
            for (Map.Entry<File, Integer> entry : dependency.getOtherFiles().entrySet()){
                list.get(c).addToMap(entry.getKey().getName());
            }
            c++;
        }


        int d=0;
        Iterator iterator2 = project.getMethodDependencies().iterator();
        while(iterator2.hasNext()){
            MethodDependency dependency = (MethodDependency) iterator2.next();
            for (Map.Entry<String, Integer> entry : dependency.getOtherMethods().entrySet()){
                list2.get(d).addToMap(entry.getKey());
            }
            d++;
        }


        for (int j=0;j<fileNumber;j++) {
            for (Map.Entry<String, String> entry : list.get(j).getConnections().entrySet()){
                String id="";
                for(IdClass idClass : list)
                {
                    if(idClass.fileName.equals(entry.getKey()))
                    {
                        id=idClass.getId();
                    }
                }
                printWriter.print("            <Dependency BacklogActivityId=\"0\" Documentation_plain=\"\" From=\""+list.get(j).getId()+"\" Id=\""+entry.getValue()+"\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T17:35:28.925\" PmLastModified=\"2019-12-17T17:35:46.484\" QualityReason_IsNull=\"true\" QualityScore=\"-1\" To=\""+id+"\" UserIDLastNumericValue=\"0\" UserID_IsNull=\"true\" Visibility=\"Unspecified\">\n");
                printWriter.print("            </Dependency>\n");
            }
        }

        for(int j=0;j<methodNumber;j++)
        {
            for(Map.Entry<String, String> entry : list2.get(j).getConnections().entrySet())
            {
                String id="";
                for(IdClass idClass : list2)
                {
                    if(idClass.fileName.equals(entry.getKey()))
                    {
                        id=idClass.getId();
                    }
                }
                printWriter1.print("            <Dependency BacklogActivityId=\"0\" Documentation_plain=\"\" From=\""+list2.get(j).getId()+"\" Id=\""+entry.getValue()+"\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T17:35:28.925\" PmLastModified=\"2019-12-17T17:35:46.484\" QualityReason_IsNull=\"true\" QualityScore=\"-1\" To=\""+id+"\" UserIDLastNumericValue=\"0\" UserID_IsNull=\"true\" Visibility=\"Unspecified\">\n");
                printWriter1.print("            </Dependency>\n");
            }
        }



        printWriter.print("\t</Models>\n");
        printWriter.print("\t<Diagrams>\n");
        printWriter.print("\t\t<ClassDiagram AlignToGrid=\"false\" AutoFitShapesSize=\"false\" ConnectionPointStyle=\"0\" ConnectorLabelOrientation=\"0\" ConnectorLineJumps=\"0\" ConnectorLineJumpsSize=\"0\" ConnectorModelElementNameAlignment=\"4\" ConnectorStyle=\"1\" DiagramBackground=\"rgb(255, 255, 255)\" Editable=\"true\" FollowDiagramParentElement=\"true\" GeneralizationSetNotation=\"2\" GridColor=\"rgb(192, 192, 192)\" GridHeight=\"10\" GridVisible=\"false\" GridWidth=\"10\" Height=\"526\" HideConnectorIfFromToIsHidden=\"0\" HideEmptyTaggedValues=\"false\" Id=\""+ PasswordGenerator.generatePassword(12)+"\" ImageHeight=\"0\" ImageScale=\"1.0\" ImageWidth=\"0\" InitializeDiagramForCreate=\"true\" Maximized=\"true\" ModelElementNameAlignment=\"4\" Name=\"Class Diagram2\" PaintConnectorThroughLabel=\"1\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T16:54:40.760\" PmLastModified=\"2019-12-17T17:35:36.480\" PointConnectorEndToCompartmentMember=\"true\" QualityScore=\"-1\" RequestFitSizeWithPromptUser=\"false\" RequestValidateSnapToGrid=\"false\" ShapePresentationOption=\"0\" ShowActivityStateNodeCaption=\"524287\" ShowAllocatedFrom=\"true\" ShowAllocatedTo=\"true\" ShowAssociationNavigationArrows=\"3\" ShowAttributeGetterSetter=\"false\" ShowAttributesCodeDetails=\"2\" ShowAttributesPropertyModifiers=\"2\" ShowAttributesType=\"1\" ShowClassEmptyCompartments=\"2\" ShowClassOwner=\"2\" ShowClassReferencedAttributes=\"true\" ShowColorLegend=\"false\" ShowConnectorName=\"0\" ShowConstraints=\"false\" ShowDefaultPackage=\"true\" ShowEllipsisForUnshownClassMembers=\"2\" ShowInformationItemOption=\"2\" ShowOperationsCodeDetails=\"2\" ShowOperationsParameters=\"1\" ShowOperationsReturnType=\"1\" ShowPMAuthor=\"false\" ShowPMDifficulty=\"false\" ShowPMDiscipline=\"false\" ShowPMIteration=\"false\" ShowPMPhase=\"false\" ShowPMPriority=\"false\" ShowPMStatus=\"false\" ShowPMVersion=\"false\" ShowPackageNameStyle=\"0\" ShowPackageOwner=\"2\" ShowParametersCodeDetails=\"2\" ShowShapeLegend=\"false\" ShowShapeStereotypeIconName=\"true\" ShowStereotypes=\"true\" ShowTaggedValues=\"false\" ShowTemplateInfoOfGeneralizationAndRealization=\"false\" SuppressImplied1MultiplicityForAttributeAndAssociationEnd=\"false\" TeamworkCreateDateTime=\"0\" TrimmedHeight=\"0\" TrimmedWidth=\"0\" Width=\"1255\" X=\"0\" Y=\"0\" ZoomRatio=\"0.4\" _globalPaletteOption=\"true\">\n");
        printWriter.print("\t\t\t<Shapes>\n");

        int x=1000;
        int y=1000;
        double war=fileNumber/4;
        for (int j=0;j<fileNumber;j++) {
            String diagId= PasswordGenerator.generatePassword(12);
            list.get(j).setDiagID(diagId);
            printWriter.print("\t\t\t\t<Class AttributeSortType=\"0\" Background=\"rgb(122, 207, 245)\" ConnectToPoint=\"true\" ConnectionPointType=\"2\" CoverConnector=\"false\" CreatorDiagramType=\"ClassDiagram\" DisplayAsRobustnessAnalysisIcon=\"true\" EnumerationLiteralSortType=\"0\" Foreground=\"rgb(0, 0, 0)\" Height=\"167\" Id=\""+list.get(j).getDiagID()+"\" InterfaceBall=\"false\" KShDrOp=\"false\" KSwCsMbSt=\"true\" LShCmMl=\"false\" LshRfAts=\"0\" MShDrAt=\"false\" MSwTpPts=\"true\" MetaModelElement=\""+list.get(j).id+"\" Model=\""+list.get(j).id+"\" ModelElementNameAlignment=\"9\" Name=\""+list.get(j).fileName+"\" OperationSortType=\"0\" OverrideAppearanceWithStereotypeIcon=\"true\" ParentConnectorDTheta=\"0.0\" ParentConnectorHeaderLength=\"40\" ParentConnectorLineLength=\"10\" PresentationOption=\"4\" PrimitiveShapeType=\"0\" ReceptionSortType=\"0\" RequestDefaultSize=\"false\" RequestFitSize=\"false\" RequestFitSizeFromCenter=\"false\" RequestResetCaption=\"false\" RequestResetCaptionFitWidth=\"false\" RequestResetCaptionSize=\"false\" RequestSetSizeOption=\"0\" Selectable=\"true\" ShowAllocatedFrom=\"0\" ShowAllocatedTo=\"0\" ShowAttributeType=\"1\" ShowAttributesCodeDetails=\"0\" ShowAttributesPropertyModifiers=\"0\" ShowAttributesType=\"0\" ShowClassMemberConstraints=\"true\" ShowEllipsisForUnshownMembers=\"0\" ShowEmptyCompartments=\"0\" ShowEnumerationLiteralType=\"1\" ShowInitialAttributeValue=\"true\" ShowOperationParameterDirection=\"false\" ShowOperationProperties=\"false\" ShowOperationRaisedExceptions=\"false\" ShowOperationSignature=\"true\" ShowOperationTemplateParameters=\"false\" ShowOperationType=\"1\" ShowOperationsCodeDetails=\"0\" ShowOperationsParameters=\"0\" ShowOperationsReturnType=\"0\" ShowOwnerOption=\"3\" ShowParameterNameInOperationSignature=\"true\" ShowParametersCodeDetails=\"0\" ShowReceptionType=\"1\" ShowStereotypeIconName=\"0\" ShowTypeOption=\"0\" SuppressImplied1MultiplicityForAttribute=\"0\" VisibilityStyle=\"1\" Width=\"252\" WpMbs=\"false\" X=\""+x+"\" Y=\""+y+"\" ZOrder=\"6\">\t\t\t\t\n");
            printWriter.print("\t\t\t\t\t<Caption Height=\"15\" InternalHeight=\"-2147483648\" InternalWidth=\"-2147483648\" Side=\"FreeMove\" Visible=\"true\" Width=\"253\" X=\"0\" Y=\"0\"/>\t\t\t\t\t\n");
            printWriter.print("\t\t\t\t</Class>\n");


            if((double)j<war) {
                x = x + 400;
            }
            else if((double)j<(war*2+1))
            {
                y=y+400;
            }
            else if((double)j<(war*3+1))
            {
                x=x-400;
            }
            else
            {
                y=y-400;
            }
        }
        printWriter.print("\t\t\t</Shapes>\n");

        printWriter.print("\t\t\t<Connectors>\n");

        for (int j=0;j<fileNumber;j++) {
            for (Map.Entry<String, String> entry : list.get(j).getConnections().entrySet()){
                String id="";
                for(IdClass idClass : list)
                {
                    if(idClass.fileName.equals(entry.getKey()))
                    {
                        id=idClass.getDiagID();
                    }
                }
                printWriter.print("\t\t\t\t<Dependency Background=\"rgb(122, 207, 245)\" ConnectorLabelOrientation=\"4\" ConnectorLineJumps=\"4\" ConnectorStyle=\"Follow Diagram\" CreatorDiagramType=\"ClassDiagram\" Foreground=\"rgb(0, 0, 0)\" From=\""+list.get(j).getDiagID()+"\" FromConnectType=\"0\" FromPinType=\"1\" FromShapeXDiff=\"0\" FromShapeYDiff=\"0\" Height=\"410\" Id=\""+ PasswordGenerator.generatePassword(12)+"\" MetaModelElement=\""+entry.getValue()+"\" Model=\""+entry.getValue()+"\" ModelElementNameAlignment=\"9\" PaintThroughLabel=\"2\" RequestRebuild=\"false\" RequestRebuildFromEnd=\"false\" RequestRebuildToEnd=\"false\" RequestResetCaption=\"false\" RequestResetCaptionFitWidth=\"false\" RequestResetCaptionSize=\"false\" Selectable=\"true\" ShowConnectorName=\"2\" To=\""+id+"\" ToConnectType=\"0\" ToPinType=\"1\" ToShapeXDiff=\"0\" ToShapeYDiff=\"0\" UseFromShapeCenter=\"true\" UseToShapeCenter=\"true\" Width=\"250\" X=\"532\" Y=\"300\" ZOrder=\"12\">\n");
                printWriter.print("\t\t\t\t</Dependency>\n");
            }
        }




        printWriter1.print("\t</Models>\n");
        printWriter1.print("\t<Diagrams>\n");
        printWriter1.print("\t\t<ClassDiagram AlignToGrid=\"false\" AutoFitShapesSize=\"false\" ConnectionPointStyle=\"0\" ConnectorLabelOrientation=\"0\" ConnectorLineJumps=\"0\" ConnectorLineJumpsSize=\"0\" ConnectorModelElementNameAlignment=\"4\" ConnectorStyle=\"1\" DiagramBackground=\"rgb(255, 255, 255)\" Editable=\"true\" FollowDiagramParentElement=\"true\" GeneralizationSetNotation=\"2\" GridColor=\"rgb(192, 192, 192)\" GridHeight=\"10\" GridVisible=\"false\" GridWidth=\"10\" Height=\"526\" HideConnectorIfFromToIsHidden=\"0\" HideEmptyTaggedValues=\"false\" Id=\""+ PasswordGenerator.generatePassword(12)+"\" ImageHeight=\"0\" ImageScale=\"1.0\" ImageWidth=\"0\" InitializeDiagramForCreate=\"true\" Maximized=\"true\" ModelElementNameAlignment=\"4\" Name=\"Class Diagram2\" PaintConnectorThroughLabel=\"1\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T16:54:40.760\" PmLastModified=\"2019-12-17T17:35:36.480\" PointConnectorEndToCompartmentMember=\"true\" QualityScore=\"-1\" RequestFitSizeWithPromptUser=\"false\" RequestValidateSnapToGrid=\"false\" ShapePresentationOption=\"0\" ShowActivityStateNodeCaption=\"524287\" ShowAllocatedFrom=\"true\" ShowAllocatedTo=\"true\" ShowAssociationNavigationArrows=\"3\" ShowAttributeGetterSetter=\"false\" ShowAttributesCodeDetails=\"2\" ShowAttributesPropertyModifiers=\"2\" ShowAttributesType=\"1\" ShowClassEmptyCompartments=\"2\" ShowClassOwner=\"2\" ShowClassReferencedAttributes=\"true\" ShowColorLegend=\"false\" ShowConnectorName=\"0\" ShowConstraints=\"false\" ShowDefaultPackage=\"true\" ShowEllipsisForUnshownClassMembers=\"2\" ShowInformationItemOption=\"2\" ShowOperationsCodeDetails=\"2\" ShowOperationsParameters=\"1\" ShowOperationsReturnType=\"1\" ShowPMAuthor=\"false\" ShowPMDifficulty=\"false\" ShowPMDiscipline=\"false\" ShowPMIteration=\"false\" ShowPMPhase=\"false\" ShowPMPriority=\"false\" ShowPMStatus=\"false\" ShowPMVersion=\"false\" ShowPackageNameStyle=\"0\" ShowPackageOwner=\"2\" ShowParametersCodeDetails=\"2\" ShowShapeLegend=\"false\" ShowShapeStereotypeIconName=\"true\" ShowStereotypes=\"true\" ShowTaggedValues=\"false\" ShowTemplateInfoOfGeneralizationAndRealization=\"false\" SuppressImplied1MultiplicityForAttributeAndAssociationEnd=\"false\" TeamworkCreateDateTime=\"0\" TrimmedHeight=\"0\" TrimmedWidth=\"0\" Width=\"1255\" X=\"0\" Y=\"0\" ZoomRatio=\"0.4\" _globalPaletteOption=\"true\">\n");
        printWriter1.print("\t\t\t<Shapes>\n");

        x=1000;
        y=1000;
        war=methodNumber/4;
        for (int j=0;j<methodNumber;j++) {
            String diagId= PasswordGenerator.generatePassword(12);
            list2.get(j).setDiagID(diagId);
            printWriter1.print("\t\t\t\t<Class AttributeSortType=\"0\" Background=\"rgb(122, 207, 245)\" ConnectToPoint=\"true\" ConnectionPointType=\"2\" CoverConnector=\"false\" CreatorDiagramType=\"ClassDiagram\" DisplayAsRobustnessAnalysisIcon=\"true\" EnumerationLiteralSortType=\"0\" Foreground=\"rgb(0, 0, 0)\" Height=\"167\" Id=\""+list2.get(j).getDiagID()+"\" InterfaceBall=\"false\" KShDrOp=\"false\" KSwCsMbSt=\"true\" LShCmMl=\"false\" LshRfAts=\"0\" MShDrAt=\"false\" MSwTpPts=\"true\" MetaModelElement=\""+list2.get(j).id+"\" Model=\""+list2.get(j).id+"\" ModelElementNameAlignment=\"9\" Name=\""+list2.get(j).fileName+"\" OperationSortType=\"0\" OverrideAppearanceWithStereotypeIcon=\"true\" ParentConnectorDTheta=\"0.0\" ParentConnectorHeaderLength=\"40\" ParentConnectorLineLength=\"10\" PresentationOption=\"4\" PrimitiveShapeType=\"0\" ReceptionSortType=\"0\" RequestDefaultSize=\"false\" RequestFitSize=\"false\" RequestFitSizeFromCenter=\"false\" RequestResetCaption=\"false\" RequestResetCaptionFitWidth=\"false\" RequestResetCaptionSize=\"false\" RequestSetSizeOption=\"0\" Selectable=\"true\" ShowAllocatedFrom=\"0\" ShowAllocatedTo=\"0\" ShowAttributeType=\"1\" ShowAttributesCodeDetails=\"0\" ShowAttributesPropertyModifiers=\"0\" ShowAttributesType=\"0\" ShowClassMemberConstraints=\"true\" ShowEllipsisForUnshownMembers=\"0\" ShowEmptyCompartments=\"0\" ShowEnumerationLiteralType=\"1\" ShowInitialAttributeValue=\"true\" ShowOperationParameterDirection=\"false\" ShowOperationProperties=\"false\" ShowOperationRaisedExceptions=\"false\" ShowOperationSignature=\"true\" ShowOperationTemplateParameters=\"false\" ShowOperationType=\"1\" ShowOperationsCodeDetails=\"0\" ShowOperationsParameters=\"0\" ShowOperationsReturnType=\"0\" ShowOwnerOption=\"3\" ShowParameterNameInOperationSignature=\"true\" ShowParametersCodeDetails=\"0\" ShowReceptionType=\"1\" ShowStereotypeIconName=\"0\" ShowTypeOption=\"0\" SuppressImplied1MultiplicityForAttribute=\"0\" VisibilityStyle=\"1\" Width=\"252\" WpMbs=\"false\" X=\""+x+"\" Y=\""+y+"\" ZOrder=\"6\">\t\t\t\t\n");
            printWriter1.print("\t\t\t\t\t<Caption Height=\"50\" InternalHeight=\"-2147483648\" InternalWidth=\"-2147483648\" Side=\"FreeMove\" Visible=\"true\" Width=\"253\" X=\"0\" Y=\"0\"/>\t\t\t\t\t\n");
            printWriter1.print("\t\t\t\t</Class>\n");



            if((double)j<war) {
                x = x + 400;
            }
            else if((double)j<(war*2+1))
            {
                y=y+400;
            }
            else if((double)j<(war*3+1))
            {
                x=x-400;
            }
            else
            {
                y=y-400;
            }
        }
        printWriter1.print("\t\t\t</Shapes>\n");


        printWriter1.print("\t\t\t<Connectors>\n");


        for (int j=0;j<methodNumber;j++) {
            for (Map.Entry<String, String> entry : list2.get(j).getConnections().entrySet()){
                String id="";
                for(IdClass idClass : list2)
                {
                    if(idClass.fileName.equals(entry.getKey()))
                    {
                        id=idClass.getDiagID();
                    }
                }
                printWriter1.print("\t\t\t\t<Dependency Background=\"rgb(122, 207, 245)\" ConnectorLabelOrientation=\"4\" ConnectorLineJumps=\"4\" ConnectorStyle=\"Follow Diagram\" CreatorDiagramType=\"ClassDiagram\" Foreground=\"rgb(0, 0, 0)\" From=\""+list2.get(j).getDiagID()+"\" FromConnectType=\"0\" FromPinType=\"1\" FromShapeXDiff=\"0\" FromShapeYDiff=\"0\" Height=\"410\" Id=\""+ PasswordGenerator.generatePassword(12)+"\" MetaModelElement=\""+entry.getValue()+"\" Model=\""+entry.getValue()+"\" ModelElementNameAlignment=\"9\" PaintThroughLabel=\"2\" RequestRebuild=\"false\" RequestRebuildFromEnd=\"false\" RequestRebuildToEnd=\"false\" RequestResetCaption=\"false\" RequestResetCaptionFitWidth=\"false\" RequestResetCaptionSize=\"false\" Selectable=\"true\" ShowConnectorName=\"2\" To=\""+id+"\" ToConnectType=\"0\" ToPinType=\"1\" ToShapeXDiff=\"0\" ToShapeYDiff=\"0\" UseFromShapeCenter=\"true\" UseToShapeCenter=\"true\" Width=\"250\" X=\"532\" Y=\"300\" ZOrder=\"12\">\n");
                printWriter1.print("\t\t\t\t</Dependency>\n");
            }
        }

        printWriter.print("\t\t\t</Connectors>\n");
        printWriter.print("\t\t</ClassDiagram>\n");
        printWriter.print("\t</Diagrams>\n");
        printWriter.print("</Project>");
        printWriter.close();

        printWriter1.print("\t\t\t</Connectors>\n");
        printWriter1.print("\t\t</ClassDiagram>\n");
        printWriter1.print("\t</Diagrams>\n");
        printWriter1.print("</Project>");
        printWriter1.close();


        /////////////////////////////////////////////////////////////////////HIST 3////////////////////////////////////

        List<UMLdata> list3=new LinkedList<>();

        Iterator iterator3 = project.getMethodDependencies().iterator();
        while(iterator3.hasNext()){
            MethodDependency dependency = (MethodDependency) iterator3.next();

            list3.add(new UMLdata(dependency.getMethodName(),dependency.getFileName()));
        }

        iterator3=list3.iterator();


        String mID="";
        String fID="";
        while(iterator3.hasNext())
        {
            UMLdata his = (UMLdata) iterator3.next();

            for(int i=0;i<list2.size();i++)
            {
                if(list2.get(i).getProject().equals(his.metodName))
                {
                    mID=list2.get(i).getId();
                }
            }
            for(int j=0;j<list.size();j++)
            {
                if(list.get(j).getProject().equals(his.fileName))
                {
                    fID=list.get(j).getId();
                }
            }
            printWriter2.print("            <Dependency BacklogActivityId=\"0\" Documentation_plain=\"\" From=\""+mID+"\" Id=\""+his.code+"\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T17:35:28.925\" PmLastModified=\"2019-12-17T17:35:46.484\" QualityReason_IsNull=\"true\" QualityScore=\"-1\" To=\""+fID+"\" UserIDLastNumericValue=\"0\" UserID_IsNull=\"true\" Visibility=\"Unspecified\">\n");
            printWriter2.print("            </Dependency>\n");
        }


/*
        for(int j=0;j<methodNumber;j++)
        {
            for(Map.Entry<String, String> entry : list3.get(j).getConnections().entrySet())
            {
                printWriter2.print("            <Dependency BacklogActivityId=\"0\" Documentation_plain=\"\" From=\""+list3.get(j).getMethodID()+"\" Id=\""+entry.getValue()+"\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T17:35:28.925\" PmLastModified=\"2019-12-17T17:35:46.484\" QualityReason_IsNull=\"true\" QualityScore=\"-1\" To=\""+entry.getKey()+"\" UserIDLastNumericValue=\"0\" UserID_IsNull=\"true\" Visibility=\"Unspecified\">\n");
                printWriter2.print("            </Dependency>\n");
            }
        }
*/




        printWriter2.print("\t</Models>\n");
        printWriter2.print("\t<Diagrams>\n");
        printWriter2.print("\t\t<ClassDiagram AlignToGrid=\"false\" AutoFitShapesSize=\"false\" ConnectionPointStyle=\"0\" ConnectorLabelOrientation=\"0\" ConnectorLineJumps=\"0\" ConnectorLineJumpsSize=\"0\" ConnectorModelElementNameAlignment=\"4\" ConnectorStyle=\"1\" DiagramBackground=\"rgb(255, 255, 255)\" Editable=\"true\" FollowDiagramParentElement=\"true\" GeneralizationSetNotation=\"2\" GridColor=\"rgb(192, 192, 192)\" GridHeight=\"10\" GridVisible=\"false\" GridWidth=\"10\" Height=\"526\" HideConnectorIfFromToIsHidden=\"0\" HideEmptyTaggedValues=\"false\" Id=\""+ PasswordGenerator.generatePassword(12)+"\" ImageHeight=\"0\" ImageScale=\"1.0\" ImageWidth=\"0\" InitializeDiagramForCreate=\"true\" Maximized=\"true\" ModelElementNameAlignment=\"4\" Name=\"Class Diagram2\" PaintConnectorThroughLabel=\"1\" PmAuthor=\"HP\" PmCreateDateTime=\"2019-12-17T16:54:40.760\" PmLastModified=\"2019-12-17T17:35:36.480\" PointConnectorEndToCompartmentMember=\"true\" QualityScore=\"-1\" RequestFitSizeWithPromptUser=\"false\" RequestValidateSnapToGrid=\"false\" ShapePresentationOption=\"0\" ShowActivityStateNodeCaption=\"524287\" ShowAllocatedFrom=\"true\" ShowAllocatedTo=\"true\" ShowAssociationNavigationArrows=\"3\" ShowAttributeGetterSetter=\"false\" ShowAttributesCodeDetails=\"2\" ShowAttributesPropertyModifiers=\"2\" ShowAttributesType=\"1\" ShowClassEmptyCompartments=\"2\" ShowClassOwner=\"2\" ShowClassReferencedAttributes=\"true\" ShowColorLegend=\"false\" ShowConnectorName=\"0\" ShowConstraints=\"false\" ShowDefaultPackage=\"true\" ShowEllipsisForUnshownClassMembers=\"2\" ShowInformationItemOption=\"2\" ShowOperationsCodeDetails=\"2\" ShowOperationsParameters=\"1\" ShowOperationsReturnType=\"1\" ShowPMAuthor=\"false\" ShowPMDifficulty=\"false\" ShowPMDiscipline=\"false\" ShowPMIteration=\"false\" ShowPMPhase=\"false\" ShowPMPriority=\"false\" ShowPMStatus=\"false\" ShowPMVersion=\"false\" ShowPackageNameStyle=\"0\" ShowPackageOwner=\"2\" ShowParametersCodeDetails=\"2\" ShowShapeLegend=\"false\" ShowShapeStereotypeIconName=\"true\" ShowStereotypes=\"true\" ShowTaggedValues=\"false\" ShowTemplateInfoOfGeneralizationAndRealization=\"false\" SuppressImplied1MultiplicityForAttributeAndAssociationEnd=\"false\" TeamworkCreateDateTime=\"0\" TrimmedHeight=\"0\" TrimmedWidth=\"0\" Width=\"1255\" X=\"0\" Y=\"0\" ZoomRatio=\"0.4\" _globalPaletteOption=\"true\">\n");
        printWriter2.print("\t\t\t<Shapes>\n");

        x=1000;
        y=1000;
        war=(fileNumber+methodNumber)/4;
        int number=fileNumber;
        char mode='p';
        int k=0;
        for (int j=0;j<number;j++)
        {
            if(mode=='p') {
                printWriter2.print("\t\t\t\t<Class AttributeSortType=\"0\" Background=\"rgb(200, 207, 245)\" ConnectToPoint=\"true\" ConnectionPointType=\"2\" CoverConnector=\"false\" CreatorDiagramType=\"ClassDiagram\" DisplayAsRobustnessAnalysisIcon=\"true\" EnumerationLiteralSortType=\"0\" Foreground=\"rgb(0, 0, 0)\" Height=\"167\" Id=\"" + list.get(j).getDiagID() + "\" InterfaceBall=\"false\" KShDrOp=\"false\" KSwCsMbSt=\"true\" LShCmMl=\"false\" LshRfAts=\"0\" MShDrAt=\"false\" MSwTpPts=\"true\" MetaModelElement=\"" + list.get(j).id + "\" Model=\"" + list.get(j).id + "\" ModelElementNameAlignment=\"9\" Name=\"" + list.get(j).fileName + "\" OperationSortType=\"0\" OverrideAppearanceWithStereotypeIcon=\"true\" ParentConnectorDTheta=\"0.0\" ParentConnectorHeaderLength=\"40\" ParentConnectorLineLength=\"10\" PresentationOption=\"4\" PrimitiveShapeType=\"0\" ReceptionSortType=\"0\" RequestDefaultSize=\"false\" RequestFitSize=\"false\" RequestFitSizeFromCenter=\"false\" RequestResetCaption=\"false\" RequestResetCaptionFitWidth=\"false\" RequestResetCaptionSize=\"false\" RequestSetSizeOption=\"0\" Selectable=\"true\" ShowAllocatedFrom=\"0\" ShowAllocatedTo=\"0\" ShowAttributeType=\"1\" ShowAttributesCodeDetails=\"0\" ShowAttributesPropertyModifiers=\"0\" ShowAttributesType=\"0\" ShowClassMemberConstraints=\"true\" ShowEllipsisForUnshownMembers=\"0\" ShowEmptyCompartments=\"0\" ShowEnumerationLiteralType=\"1\" ShowInitialAttributeValue=\"true\" ShowOperationParameterDirection=\"false\" ShowOperationProperties=\"false\" ShowOperationRaisedExceptions=\"false\" ShowOperationSignature=\"true\" ShowOperationTemplateParameters=\"false\" ShowOperationType=\"1\" ShowOperationsCodeDetails=\"0\" ShowOperationsParameters=\"0\" ShowOperationsReturnType=\"0\" ShowOwnerOption=\"3\" ShowParameterNameInOperationSignature=\"true\" ShowParametersCodeDetails=\"0\" ShowReceptionType=\"1\" ShowStereotypeIconName=\"0\" ShowTypeOption=\"0\" SuppressImplied1MultiplicityForAttribute=\"0\" VisibilityStyle=\"1\" Width=\"252\" WpMbs=\"false\" X=\"" + x + "\" Y=\"" + y + "\" ZOrder=\"6\">\t\t\t\t\n");
                printWriter2.print("\t\t\t\t\t<Caption Height=\"15\" InternalHeight=\"-2147483648\" InternalWidth=\"-2147483648\" Side=\"FreeMove\" Visible=\"true\" Width=\"253\" X=\"0\" Y=\"0\"/>\t\t\t\t\t\n");
                printWriter2.print("\t\t\t\t</Class>\n");
            }
            else
            {
                printWriter2.print("\t\t\t\t<Class AttributeSortType=\"0\" Background=\"rgb(122, 207, 245)\" ConnectToPoint=\"true\" ConnectionPointType=\"2\" CoverConnector=\"false\" CreatorDiagramType=\"ClassDiagram\" DisplayAsRobustnessAnalysisIcon=\"true\" EnumerationLiteralSortType=\"0\" Foreground=\"rgb(0, 0, 0)\" Height=\"167\" Id=\""+list2.get(j).getDiagID()+"\" InterfaceBall=\"false\" KShDrOp=\"false\" KSwCsMbSt=\"true\" LShCmMl=\"false\" LshRfAts=\"0\" MShDrAt=\"false\" MSwTpPts=\"true\" MetaModelElement=\""+list2.get(j).id+"\" Model=\""+list2.get(j).id+"\" ModelElementNameAlignment=\"9\" Name=\""+list2.get(j).fileName+"\" OperationSortType=\"0\" OverrideAppearanceWithStereotypeIcon=\"true\" ParentConnectorDTheta=\"0.0\" ParentConnectorHeaderLength=\"40\" ParentConnectorLineLength=\"10\" PresentationOption=\"4\" PrimitiveShapeType=\"0\" ReceptionSortType=\"0\" RequestDefaultSize=\"false\" RequestFitSize=\"false\" RequestFitSizeFromCenter=\"false\" RequestResetCaption=\"false\" RequestResetCaptionFitWidth=\"false\" RequestResetCaptionSize=\"false\" RequestSetSizeOption=\"0\" Selectable=\"true\" ShowAllocatedFrom=\"0\" ShowAllocatedTo=\"0\" ShowAttributeType=\"1\" ShowAttributesCodeDetails=\"0\" ShowAttributesPropertyModifiers=\"0\" ShowAttributesType=\"0\" ShowClassMemberConstraints=\"true\" ShowEllipsisForUnshownMembers=\"0\" ShowEmptyCompartments=\"0\" ShowEnumerationLiteralType=\"1\" ShowInitialAttributeValue=\"true\" ShowOperationParameterDirection=\"false\" ShowOperationProperties=\"false\" ShowOperationRaisedExceptions=\"false\" ShowOperationSignature=\"true\" ShowOperationTemplateParameters=\"false\" ShowOperationType=\"1\" ShowOperationsCodeDetails=\"0\" ShowOperationsParameters=\"0\" ShowOperationsReturnType=\"0\" ShowOwnerOption=\"3\" ShowParameterNameInOperationSignature=\"true\" ShowParametersCodeDetails=\"0\" ShowReceptionType=\"1\" ShowStereotypeIconName=\"0\" ShowTypeOption=\"0\" SuppressImplied1MultiplicityForAttribute=\"0\" VisibilityStyle=\"1\" Width=\"252\" WpMbs=\"false\" X=\""+x+"\" Y=\""+y+"\" ZOrder=\"6\">\t\t\t\t\n");
                printWriter2.print("\t\t\t\t\t<Caption Height=\"50\" InternalHeight=\"-2147483648\" InternalWidth=\"-2147483648\" Side=\"FreeMove\" Visible=\"true\" Width=\"253\" X=\"0\" Y=\"0\"/>\t\t\t\t\t\n");
                printWriter2.print("\t\t\t\t</Class>\n");
            }

            if((double)k<war) {
                x = x + 400;
            }
            else if((double)k<(war*2+1))
            {
                y=y+400;
            }
            else if((double)k<(war*3+1))
            {
                x=x-400;
            }
            else
            {
                y=y-400;
            }

            if(j==fileNumber-1 && mode=='p') {
                number = methodNumber;
                j=0;
                mode = 'm';
            }
            k++;
        }
        printWriter2.print("\t\t\t</Shapes>\n");

        printWriter2.print("\t\t\t<Connectors>\n");

        Iterator iterator4=list3.iterator();
        String mdiagID="f";
        String fdiagID="";
        System.out.println(list2.size());
        while(iterator4.hasNext())
        {
            UMLdata his = (UMLdata) iterator4.next();

            for(int i=0;i<list2.size();i++)
            {
                if(list2.get(i).getProject().equals(his.metodName))
                {
                    mdiagID=list2.get(i).getDiagID();
                }

            }
            for(int j=0;j<list.size();j++)
            {
                if(list.get(j).getProject().equals(his.fileName))
                {
                    fdiagID=list.get(j).getDiagID();
                }
            }
            printWriter2.print("\t\t\t\t<Dependency Background=\"rgb(122, 207, 245)\" ConnectorLabelOrientation=\"4\" ConnectorLineJumps=\"4\" ConnectorStyle=\"Follow Diagram\" CreatorDiagramType=\"ClassDiagram\" Foreground=\"rgb(0, 0, 0)\" From=\""+mdiagID+"\" FromConnectType=\"0\" FromPinType=\"1\" FromShapeXDiff=\"0\" FromShapeYDiff=\"0\" Height=\"410\" Id=\""+ PasswordGenerator.generatePassword(12)+"\" MetaModelElement=\""+his.code+"\" Model=\""+his.code+"\" ModelElementNameAlignment=\"9\" PaintThroughLabel=\"2\" RequestRebuild=\"false\" RequestRebuildFromEnd=\"false\" RequestRebuildToEnd=\"false\" RequestResetCaption=\"false\" RequestResetCaptionFitWidth=\"false\" RequestResetCaptionSize=\"false\" Selectable=\"true\" ShowConnectorName=\"2\" To=\""+fdiagID+"\" ToConnectType=\"0\" ToPinType=\"1\" ToShapeXDiff=\"0\" ToShapeYDiff=\"0\" UseFromShapeCenter=\"true\" UseToShapeCenter=\"true\" Width=\"250\" X=\"532\" Y=\"300\" ZOrder=\"12\">\n");
            printWriter2.print("\t\t\t\t</Dependency>\n");
        }





        printWriter2.print("\t\t\t</Connectors>\n");
        printWriter2.print("\t\t</ClassDiagram>\n");
        printWriter2.print("\t</Diagrams>\n");
        printWriter2.print("</Project>");
        printWriter2.close();




    }
}
